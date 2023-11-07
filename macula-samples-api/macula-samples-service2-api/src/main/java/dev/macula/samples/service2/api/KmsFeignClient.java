package dev.macula.samples.service2.api;

import dev.macula.boot.result.PageVO;
import dev.macula.samples.service2.api.fallback.AbstracKmsFeignFallbackFactory;
import dev.macula.samples.service2.form.KmsForm;
import dev.macula.samples.service2.query.KmsPageQuery;
import dev.macula.samples.service2.vo.kms.KmsVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(value = "macula-samples-service2", contextId = "kmsFeignClient",
        fallbackFactory = AbstracKmsFeignFallbackFactory.class)
public interface KmsFeignClient {
    @GetMapping("/api/v1/kms")
    PageVO<KmsVo> listKmss(@SpringQueryMap KmsPageQuery queryParams);

    @PostMapping("/api/v1/kms")
    boolean saveKms(@Valid @RequestBody KmsForm formData);

    @PutMapping(value = "/api/v1/kms/{kmsId}")
    boolean updateKms(@PathVariable Long kmsId, @Valid @RequestBody KmsForm formData);

    @DeleteMapping("/api/v1/kms/{ids}")
    boolean deleteKmss(@PathVariable("ids") String ids);

    @GetMapping("/api/v1/kms/existApp/{ids}")
    boolean existsApp(@PathVariable("ids") String ids);
}