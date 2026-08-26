package dev.macula.samples.service2.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.macula.samples.service2.converter.KmsConverter;
import dev.macula.samples.service2.form.KmsForm;
import dev.macula.samples.service2.mapper.KmsMapper;
import dev.macula.samples.service2.pojo.bo.KmsBo;
import dev.macula.samples.service2.pojo.entity.Kms;
import dev.macula.samples.service2.query.KmsPageQuery;
import dev.macula.samples.service2.service.KmsService;
import dev.macula.samples.service2.utils.RSAUtil;
import dev.macula.samples.service2.vo.kms.KmsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KmsServiceImpl extends ServiceImpl<KmsMapper, Kms> implements KmsService {

    private final KmsConverter kmsConverter;

    @Override
    public Page<KmsVo> listKmss(KmsPageQuery queryParams) {
        Page<KmsBo> page = new Page(queryParams.getPageNum(), queryParams.getPageSize());
        Page<KmsBo> bo = this.baseMapper.listKmsPages(page, queryParams);
        return kmsConverter.bo2Vo(bo);
    }

    @Override
    @Transactional
    public boolean saveKms(KmsForm kmsForm) {
        Kms kms = kmsConverter.form2Entity(kmsForm);
        Map<String, String> keyPair = RSAUtil.getKeyPair();
        Assert.notEmpty(keyPair, "生成RSA密钥对失败！");
        kms.setPublicKey(keyPair.get("publicKey"));
        kms.setPrivateKey(keyPair.get("privateKey"));
        return this.save(kms);
    }

    @Override
    @Transactional
    public boolean updateKms(Long kmsId, KmsForm kmsForm) {
        Kms kms = kmsConverter.form2Entity(kmsForm);
        kms.setId(kmsId);
        return this.updateById(kms);
    }

    @Override
    @Transactional
    public boolean deleteKmss(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的密钥数据为空");
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    @Override
    public boolean existsApp(String idsStr) {
        List<Long> ids = Arrays.stream(idsStr.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return exists(new LambdaQueryWrapper<Kms>().in(Kms::getAppId, ids));
    }
}