/*
 * Copyright (c) 2023 Macula
 *   macula.dev, China
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.macula.samples.service1.controller;

import dev.macula.boot.exception.BizException;
import dev.macula.samples.service1.errors.Service1ErrorCodes;
import dev.macula.samples.service1.vo.app.ApplicationVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * {@code EchoController} ECHO服务
 *
 * @author rain
 * @since 2023/8/29 10:43
 */
@Tag(name = "ECHO服务", description = "ECHO服务")
@RestController
@RequestMapping("/api/v1/echo")
public class EchoController {
    @Operation(summary = "获取hello")
    @Parameter(name = "echo字符串")
    @GetMapping(value = "/hello")
    public String hello(@RequestParam("echo") String echo) {
        return "Hello World, " + echo;
    }

    @Operation(summary = "POST演示")
    @Parameter(name = "应用VO")
    @PostMapping(value = "/app")
    public ApplicationVO helloApp(@RequestBody ApplicationVO vo) {
        vo.setHomepage("http://xxx/test:8080/api");
        if (vo.getCreateTime() == null) {
            throw new BizException(Service1ErrorCodes.BIZ_APP_ERROR, "test");
        }
        return vo;
    }
}
