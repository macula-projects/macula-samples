package dev.macula.samples.service2.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dev.macula.samples.service2.form.KmsForm;
import dev.macula.samples.service2.query.KmsPageQuery;
import dev.macula.samples.service2.service.KmsService;
import dev.macula.samples.service2.vo.kms.KmsVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 密钥控制器
 */
@Tag(name = "密钥管理", description = "密钥管理")
@RestController
@RequestMapping("/api/v1/kms")
@RequiredArgsConstructor
public class KmsController {

    private final KmsService kmsService;

    @Operation(summary = "获取密钥列表分页")
    @Parameter(name = "查询参数")
    @GetMapping
    public Page<KmsVo> listKmss(KmsPageQuery queryParams) {
        return kmsService.listKmss(queryParams);
    }

    @Operation(summary = "新增密钥")
    @Parameter(name = "密钥表单数据")
    @PostMapping
    public boolean saveKms(@Valid @RequestBody KmsForm formData) {
        return kmsService.saveKms(formData);
    }

    @Operation(summary = "修改密钥")
    @Parameter(name = "密钥ID")
    @Parameter(name = "密钥表单数据")
    @PutMapping(value = "/{kmsId}")
    public boolean updateKms(@PathVariable Long kmsId, @Valid @RequestBody KmsForm formData) {
        return kmsService.updateKms(kmsId, formData);
    }

    @Operation(summary = "删除密钥")
    @Parameter(name = "密钥ID，多个以英文逗号(,)分割")
    @DeleteMapping("/{ids}")
    public boolean deleteKmss(@PathVariable("ids") String ids) {
        return kmsService.deleteKmss(ids);
    }

    @Operation(summary = "应用id列表中存在密钥关系")
    @Parameter(name = "应用id，多个以英文逗号(,)分割")
    @GetMapping("/existApp/{ids}")
    public boolean existsApp(@PathVariable("ids") String ids){
        return kmsService.existsApp(ids);
    }
}