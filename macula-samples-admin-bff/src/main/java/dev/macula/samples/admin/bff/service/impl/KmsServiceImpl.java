package dev.macula.samples.admin.bff.service.impl;

import dev.macula.boot.result.PageVO;
import dev.macula.samples.admin.bff.service.KmsService;
import dev.macula.samples.service2.api.KmsFeignClient;
import dev.macula.samples.service2.form.KmsForm;
import dev.macula.samples.service2.query.KmsPageQuery;
import dev.macula.samples.service2.vo.kms.KmsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * {@code KmsServiceImpl} 密钥管理服务方法
 *
 * @author rain
 * @since 2023/11/9 18:31
 */
@Component
@RequiredArgsConstructor
public class KmsServiceImpl implements KmsService {
    private final KmsFeignClient kmsFeignClient;
    @Override
    public PageVO<KmsVo> listKmss(KmsPageQuery queryParams) {
        return kmsFeignClient.listKmss(queryParams);
    }

    @Override
    public boolean saveKms(KmsForm kmsForm) {
        return kmsFeignClient.saveKms(kmsForm);
    }

    @Override
    public boolean updateKms(Long kmsId, KmsForm kmsForm) {
        return kmsFeignClient.updateKms(kmsId, kmsForm);
    }

    @Override
    public boolean deleteKmss(String idsStr) {
        return kmsFeignClient.deleteKmss(idsStr);
    }

    @Override
    public boolean existsApp(String idsStr) {
        return kmsFeignClient.existsApp(idsStr);
    }
}
