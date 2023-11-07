package dev.macula.samples.service2.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "密钥表单对象")
@Data
public class KmsForm {
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "应用ID")
    private Long appId;
    @Schema(description = "密钥名称")
    private String keyName;
}