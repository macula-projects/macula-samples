package dev.macula.samples.service1.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import dev.macula.boot.starter.mp.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("application")
public class Application extends BaseEntity {

    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 应用主页URL
     */
    private String homepage;

    /**
     * 应用AppKey
     */
    private String ak;

    /**
     * 应用SecretKey
     */
    private String sk;

    /**
     * 负责人
     */
    private String manager;

    /**
     * 维护人
     */
    private String maintainer;

    /**
     * 移动电话
     */
    private String mobile;

    /**
     * 应用编码
     */
    private String code;

    /**
     * 可访问路径正则
     */
    private String accessPath;

    /**
     * 是否允许回传属性
     */
    private boolean useAttrs;

    /**
     * 允许回传的属性
     */
    private String allowedAttrs;
}
