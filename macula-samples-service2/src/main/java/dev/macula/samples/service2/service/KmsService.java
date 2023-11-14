package dev.macula.samples.service2.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import dev.macula.samples.service2.form.KmsForm;
import dev.macula.samples.service2.pojo.entity.Kms;
import dev.macula.samples.service2.query.KmsPageQuery;
import dev.macula.samples.service2.vo.kms.KmsVo;

/**
 * 密钥业务接口
 */
public interface KmsService extends IService<Kms> {
    /**
     * 密钥分页列表
     *
     * @return 密钥列表
     */
    Page<KmsVo> listKmss(KmsPageQuery queryParams);

    /**
     * 新增密钥
     *
     * @param kmsForm 密钥表单对象
     * @return 是否成功, boolean
     */
    boolean saveKms(KmsForm kmsForm);

    /**
     * 修改密钥
     *
     * @param kmsId   密钥ID
     * @param kmsForm 密钥表单对象
     * @return 是否成功，boolean
     */
    boolean updateKms(Long kmsId, KmsForm kmsForm);

    /**
     * 删除密钥
     *
     * @param idsStr 密钥ID，多个以英文逗号(,)分割
     * @return 是否成功，boolean
     */
    boolean deleteKmss(String idsStr);

    /**
     * 应用id中是否存在绑定kms密钥
     * @param idsStr 应用ID，多个以英文逗号(,)分割
     * @return
     */
    boolean existsApp(String idsStr);
}