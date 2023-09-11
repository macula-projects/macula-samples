package dev.macula.samples.service1.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.macula.samples.service1.converter.ApplicationConverter;
import dev.macula.samples.service1.form.ApplicationForm;
import dev.macula.samples.service1.mapper.ApplicationMapper;
import dev.macula.samples.service1.pojo.bo.ApplicationBO;
import dev.macula.samples.service1.pojo.entity.Application;
import dev.macula.samples.service1.query.ApplicationPageQuery;
import dev.macula.samples.service1.service.ApplicationService;
import dev.macula.samples.service1.vo.app.ApplicationVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {

    private final ApplicationConverter applicationConverter;

    @Override
    public Page<ApplicationVO> listApplicationPages(ApplicationPageQuery queryParams) {
        Page<Application> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        Page<ApplicationBO> bo = this.baseMapper.listApplicationPages(page, queryParams);
        return applicationConverter.bo2Vo(bo);
    }

    @Override
    @Transactional
    public boolean saveApplication(ApplicationForm appForm) {
        Application application = applicationConverter.form2Entity(appForm);
        return this.save(application);
    }

    @Override
    @Transactional
    public boolean updateApplication(Long appId, ApplicationForm appForm) {
        Application application = applicationConverter.form2Entity(appForm);
        application.setId(appId);
        return this.updateById(application);
    }

    @Override
    @Transactional
    public boolean deleteApplications(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的应用数据为空");
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    @Override
    @Transactional
    public boolean addMaintainer(Long appId, ApplicationForm appForm) {
        Application application = this.getById(appId);
        application.setMaintainer(appForm.getMaintainer());
        return this.updateById(application);
    }
}
