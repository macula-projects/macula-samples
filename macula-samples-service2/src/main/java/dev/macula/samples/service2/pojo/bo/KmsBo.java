package dev.macula.samples.service2.pojo.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Schema(description = "密钥应用多表查询对象")
@Data
public class KmsBo implements Serializable {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "应用对应ID")
    private Long appId;

    @Schema(description = "应用编码")
    private String appCode;

    @Schema(description = "应用铝盘")
    private String appName;

    @Schema(description = "密钥名称")
    private String keyName;
}