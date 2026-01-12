package dev.macula.samples.service2.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dev.macula.samples.service2.form.KmsForm;
import dev.macula.samples.service2.pojo.bo.KmsBo;
import dev.macula.samples.service2.pojo.entity.Kms;
import dev.macula.samples.service2.vo.kms.KmsVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 密钥对象转换器
 */
@Mapper
public interface KmsConverter {

    Page<KmsVo> bo2Vo(Page<KmsBo> bo);

    Kms form2Entity(KmsForm kmsForm);
}