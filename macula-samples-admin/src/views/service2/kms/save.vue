<template>
    <el-dialog :title="titleMap[mode]" v-model="visible" :width="500" destroy-on-close @closed="$emit('closed')">
      <el-form :model="form" :rules="rules" :disabled="mode=='show'" ref="dialogForm" label-width="100px"
               label-position="left">
        <el-form-item label="密钥名称" prop="keyName">
          <el-input v-model="form.keyName" placeholder="请输入密钥名称" clearable></el-input>
        </el-form-item>
        <el-form-item label="所属应用" prop="appId">
                  <sc-table-select :placeholder="form.appName" v-model="form.scTableApp" :disabled="mode=='edit'" :apiObj="apiObj" :table-width="250" :props="props">
                      <el-table-column prop="code" label="应用编码" width="200"></el-table-column>
            <el-table-column prop="applicationName" label="应用名称" width="200"></el-table-column>
                  </sc-table-select>
              </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="visible=false">取 消</el-button>
        <el-button v-if="mode!='show'" type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
      </template>
    </el-dialog>
  </template>
  
  
  <script>
  export default {
    emits: ['success', 'closed'],
    data() {
      return {
        mode: "add",
        titleMap: {
          add: '新增密钥',
          edit: '编辑密钥'
        },
        visible: false,
        isSaveing: false,
        apiObj: this.$API.service1_application.application.listPages,
        //表单数据
        form: {
          id: "",
          keyName: "",
          appId: "",
          appName: "",
          appCode: "",
          scTableApp: {}
        },
        //验证规则
        rules: {
          keyName: [
            {required: true, message: '请输入密钥名称'}
          ],
          applicationId: [
            {required: true, message: '请选择应用编码'}
          ]
        },
        props: {
          label: 'applicationName',
          value: 'id',
        },
      }
    },
    methods: {
      //显示
      open(mode = 'add') {
        this.mode = mode;
        this.visible = true;
        return this
      },
      //表单提交方法
      submit() {
        this.$refs.dialogForm.validate(async (valid) => {
          if (valid) {
            this.isSaveing = true;
            this.form.appId = this.form.scTableApp.id
            if (this.mode == 'add') {
              // 调用相关接口
              var res = await this.$API.service2_kms.kms.add.post(this.form);
            } else {
              var res = await this.$API.service2_kms.kms.edit.put(this.form, this.form.id);
            }
            this.isSaveing = false;
            if (res.success) {
              this.$emit('success', this.form, this.mode)
              this.visible = false;
              ElMessage.success("操作成功")
            } else {
              ElMessageBox.alert(res.cause || res.msg, "提示", {type: 'error'})
            }
          } else {
            return false;
          }
        })
      },
      //表单注入数据
      setData(data) {
        this.form.id = data.id
        this.form.keyName = data.keyName
        this.form.appId = data.appId
        this.form.appCode = data.appCode
        this.form.appName = data.appName
        this.form.scTableApp = {
          id: this.form.appId,
          applicationName: this.form.appName,
          code: this.form.appCode
        }
        //可以和上面一样单个注入，也可以像下面一样直接合并进去
        //Object.assign(this.form, data)
      }
    }
  }
  </script>
  
  <style>
  </style>