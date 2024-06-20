import config from '@/config'
import http from '@/utils/request'

export default{
    kms: {
        listPages: {
            url: `${config.API_URL}/${config.MODEL.admin}/api/v1/kms`,
            name: "密钥分页列表",
            get: async function (params) {
                return await http.get(this.url, params);
            }
        },
        add: {
            url: `${config.API_URL}/${config.MODEL.admin}/api/v1/kms`,
            name: "添加密钥",
            post: async function (data = {}) {
                return await http.post(this.url, data)
            }
        },
        edit: {
            url: `${config.API_URL}/${config.MODEL.admin}/api/v1/kms`,
            name: "编辑密钥",
            put: async function (data = {}, id) {
                var reqUrl = this.url + '/' + id
                return await http.put(reqUrl, data)
            }
        },
        del: {
            url: `${config.API_URL}/${config.MODEL.admin}/api/v1/kms`,
            name: "删除密钥",
            delete: async function (data = {}) {
                var reqUrl = this.url + '/' + data
                return await http.delete(reqUrl, data)
            }
        },
    }
}