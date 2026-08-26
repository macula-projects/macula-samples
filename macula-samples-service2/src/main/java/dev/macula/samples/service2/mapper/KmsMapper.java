package dev.macula.samples.service2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dev.macula.samples.service2.pojo.bo.KmsBo;
import dev.macula.samples.service2.pojo.entity.Kms;
import dev.macula.samples.service2.query.KmsPageQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KmsMapper extends BaseMapper<Kms> {
    Page<KmsBo> listKmsPages(IPage<KmsBo> page, KmsPageQuery queryParams);
}