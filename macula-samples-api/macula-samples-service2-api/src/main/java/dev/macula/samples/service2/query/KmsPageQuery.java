package dev.macula.samples.service2.query;

import dev.macula.boot.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * kms分页查询实体
 */
@Data
public class KmsPageQuery extends BasePageQuery {
    @Schema(description = "关键字(密钥名称)")
    private String keywords;
}