package dev.macula.samples.service2.pojo.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 密钥应用多表连接业务对象
 */
@Data
public class KmsBo implements Serializable {

    private Long id;

    /**
     * 应用对应ID
     */
    private Long appId;

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 密钥名称
     */
    private String keyName;
}