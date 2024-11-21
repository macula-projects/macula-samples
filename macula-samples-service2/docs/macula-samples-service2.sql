-- `macula-samples`.kms definition

CREATE TABLE `kms` (
                       `id` bigint NOT NULL AUTO_INCREMENT COMMENT '表id,自增主键',
                       `app_id` varchar(100) NOT NULL COMMENT '应用id，密钥所属应用',
                       `key_name` varchar(30) NOT NULL COMMENT '密钥名称',
                       `public_key` longtext NOT NULL COMMENT '密钥公钥',
                       `private_key` longtext COMMENT '密钥私钥',
                       `create_by` varchar(50) NOT NULL COMMENT '创建人， 自动填充字段',
                       `create_time` datetime NOT NULL COMMENT '创建时间， 自动填充字段',
                       `last_update_by` varchar(50) NOT NULL COMMENT '最后修改人， 自动填充字段',
                       `last_update_time` datetime NOT NULL COMMENT '最后修改时间， 自动填充字段',
                       PRIMARY KEY (`id`),
                       KEY `key_name` (`key_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3 COMMENT='密钥表';