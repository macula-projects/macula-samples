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

package dev.macula.samples.service2.api.fallback;

import dev.macula.samples.service2.api.KmsFeignClient;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * {@code AbstracKmsFeignFallbackFactory} kms服务远程调用降级工厂，具体实现需要调用方实现并注册为Spring Bean
 *
 * @author rain
 * @since 2023/8/29 11:42
 */
public abstract class AbstracKmsFeignFallbackFactory implements FallbackFactory<KmsFeignClient> {

}