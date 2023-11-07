<template>
    <el-container>
      <el-header>
        <div class="left-panel">
          <el-button type="primary" icon="el-icon-plus" @click="add"></el-button>
          <el-button type="danger" plain icon="el-icon-delete" :disabled="selection.length==0"
                     @click="batch_del"></el-button>
        </div>
        <div class="right-panel">
          <div class="right-panel-search">
            <el-input v-model="search.keywords" placeholder="密钥名称 / 应用名称 / 应用编码" clearable></el-input>
            <el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
          </div>
        </div>
      </el-header>
      <el-main class="nopadding">
        <scTable ref="table" :apiObj="apiObj" @selection-change="selectionChange" stripe>
          <el-table-column type="selection" width="50"></el-table-column>
          <el-table-column label="密钥名称" prop="keyName" width="170"></el-table-column>
          <el-table-column label="应用名称" prop="appName" width="150"></el-table-column>
          <el-table-column label="应用编码" prop="appCode" width="150"></el-table-column>
          <el-table-column label="操作" fixed="right" align="right" width="120">
            <template #default="scope">
              <el-button-group>
                <el-button text type="primary" size="small" @click="table_edit(scope.row, scope.$index)">编辑</el-button>
                <el-popconfirm title="确定删除吗？" @confirm="table_del(scope.row, scope.$index)">
                  <template #reference>
                    <el-button text type="primary" size="small">删除</el-button>
                  </template>
                </el-popconfirm>
              </el-button-group>
            </template>
          </el-table-column>
        </scTable>
      </el-main>
    </el-container>
    <save-dialog v-if="dialog.save" ref="saveDialog" @success="handleSuccess" @closed="dialog.save=false"></save-dialog>
  </template>
  
  <script>
  import saveDialog from './save'
  export default{
      name: "kms",
      components: {
        saveDialog
      },
      data () {
          return {
              selection: [],
              search: {
                  keywords: ''
              },
              dialog: {
                save: false,
                list: false,
                show: false
              },
              apiObj: this.$API.service2_kms.kms.listPages
          }
      },
      methods: {
          //添加
          add() {
          this.dialog.save = true
          this.$nextTick(() => {
              this.$refs.saveDialog.open()
          })
          },
          //编辑
          table_edit(row) {
          this.dialog.save = true
          this.$nextTick(() => {
              this.$refs.saveDialog.open('edit').setData(row)
          })
          },
          //删除
          async table_del(row, index) {
          var reqData = row.id
          var res = await this.$API.service2_kms.kms.del.delete(reqData);
          if (res.success) {
              //这里选择刷新整个表格 OR 插入/编辑现有表格数据
              // this.$refs.table.tableData.splice(index, 1);
              this.$refs.table.refresh()
              ElMessage.success("删除成功")
          } else {
              ElMessageBox.alert(res.cause || res.msg, "提示", {type: 'error'})
          }
          },
          //表格选择后回调事件
          selectionChange(selection) {
            this.selection = selection;
          },
          //批量删除
          async batch_del() {
          ElMessageBox.confirm(`确定删除选中的 ${this.selection.length} 项吗？`, '提示', {
              type: 'warning'
          }).then(() => {
              let ids = this.selection.map(item => item.id).join(",")
              this.selection.forEach(item => {
                this.$refs.table.tableData.forEach((itemI, indexI) => {
                    if (item.id === itemI.id) {
                    this.$refs.table.tableData.splice(indexI, 1)
                    }
                })
              })
              if(ids){
                var res = this.$API.service2_kms.kms.del.delete(ids)
              }
              //loading.close();
              ElMessage.success("操作成功")
          }).catch(() => {
          })
          },
          //搜索
          async upsearch() {
          this.$refs.table.upData(this.search)
          },
          //本地更新数据
          handleSuccess(data, mode) {
          this.$refs.table.refresh()
          },
      }
  }
  </script>
  
  <style scoped>
  </style>