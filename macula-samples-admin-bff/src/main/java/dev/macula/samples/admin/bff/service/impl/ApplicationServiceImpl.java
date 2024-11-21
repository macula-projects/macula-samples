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

package dev.macula.samples.admin.bff.service.impl;

import cn.hutool.core.lang.Assert;
import dev.macula.boot.result.PageVO;
import dev.macula.samples.admin.bff.service.ApplicationService;
import dev.macula.samples.admin.bff.service.KmsService;
import dev.macula.samples.service1.api.ApplicationFeignClient;
import dev.macula.samples.service1.form.ApplicationForm;
import dev.macula.samples.service1.query.ApplicationPageQuery;
import dev.macula.samples.service1.vo.app.ApplicationVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * {@code ApplicationServiceImpl} 应用管理服务方法
 *
 * @author rain
 * @since 2023/10/9 18:31
 */
@Component
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationFeignClient applicationFeignClient;
    private final KmsService kmsService;

    @Override
    public PageVO<ApplicationVO> listApplicationPages(ApplicationPageQuery queryParams) {
        return applicationFeignClient.listApplications(queryParams);
    }

    @Override
    public boolean saveApplication(ApplicationForm appForm) {
        return applicationFeignClient.saveApplication(appForm);
    }

    @Override
    public boolean updateApplication(Long appId, ApplicationForm appForm) {
        return applicationFeignClient.updateApplication(appId, appForm);
    }

    @Override
    public boolean deleteApplications(String idsStr) {
        Assert.isFalse(kmsService.existsApp(idsStr), "应用数据被绑定删除失败");
        return applicationFeignClient.deleteApplications(idsStr);
    }

    @Override
    public boolean addMaintainer(Long appId, ApplicationForm appForm) {
        return applicationFeignClient.addMaintainer(appId, appForm);
    }
}
