package dev.macula.samples.service2.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import dev.macula.boot.starter.mp.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("kms")
public class Kms  extends BaseEntity {
    /**
     * 应用id，密钥所属应用
     */
    private Long appId;

    /**
     * 密钥名称
     */
    private String keyName;

    /**
     * 密钥公钥
     */
    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    private String publicKey;

    /**
     * 密钥私钥
     */
    @TableField(fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    private String privateKey;
}