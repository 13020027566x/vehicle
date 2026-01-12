CREATE DATABASE IF NOT EXISTS `vehicle` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `vehicle`;
--
-- 用户管理-用户
--
DROP TABLE IF EXISTS `su_user`;
CREATE TABLE `su_user`
(
    `id`               VARCHAR(32) NOT NULL COMMENT '主键',

    `name`             VARCHAR(32) NOT NULL COMMENT '名称',
    `login_name`       VARCHAR(64) NOT NULL COMMENT '登录名',
    `code`             VARCHAR(64)      DEFAULT '' COMMENT '编码',
    `pwd`              VARCHAR(64) NOT NULL COMMENT '密码',
    `salt`             VARCHAR(64)      DEFAULT '' COMMENT '加密盐',
    `mobile`           VARCHAR(11)      DEFAULT '' COMMENT '手机号',

    `status_val`       INT UNSIGNED     DEFAULT 0 COMMENT '用户状态值:1000=启用,1001=停用',
    `status_code`      VARCHAR(16)      DEFAULT '' COMMENT '用户状态编码:字典',

    `last_access_time` DATETIME(3)      DEFAULT NULL COMMENT '最近访问时间',
    `total_amount`     DECIMAL(19, 4)   DEFAULT NULL COMMENT '账户余额（符合 GAAP 原则）',
    `remark`           VARCHAR(255)     DEFAULT '' COMMENT '备注',

    `create_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '创建时间戳',
    `create_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        VARCHAR(64)      DEFAULT '' COMMENT '创建人ID',
    `create_name`      VARCHAR(32)      DEFAULT '' COMMENT '创建人名称',
    `update_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '更新时间戳',
    `update_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`        VARCHAR(64)      DEFAULT '' COMMENT '更新人ID',
    `update_name`      VARCHAR(32)      DEFAULT '' COMMENT '更新人名称',
    `is_del`           TINYINT UNSIGNED DEFAULT 0 COMMENT '是否删除',
    `is_test`          TINYINT UNSIGNED DEFAULT 0 COMMENT '是否测试',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户';

--
-- 用户管理-用户角色
--
DROP TABLE IF EXISTS `su_user_role`;
CREATE TABLE `su_user_role`
(
    `id`          VARCHAR(32) NOT NULL COMMENT '主键',

    `user_id`     VARCHAR(64) NOT NULL COMMENT '用户ID',
    `user_name`   VARCHAR(32)      DEFAULT '' COMMENT '用户名称',
    `user_code`   VARCHAR(64)      DEFAULT '' COMMENT '用户编码',

    `role_id`     VARCHAR(64) NOT NULL COMMENT '角色ID',
    `role_name`   VARCHAR(32)      DEFAULT '' COMMENT '角色名称',
    `role_code`   VARCHAR(64)      DEFAULT '' COMMENT '角色编码',

    `create_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '创建时间戳',
    `create_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        VARCHAR(64)      DEFAULT '' COMMENT '创建人ID',
    `create_name`      VARCHAR(32)      DEFAULT '' COMMENT '创建人名称',
    `update_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '更新时间戳',
    `update_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`        VARCHAR(64)      DEFAULT '' COMMENT '更新人ID',
    `update_name`      VARCHAR(32)      DEFAULT '' COMMENT '更新人名称',
    `is_del`           TINYINT UNSIGNED DEFAULT 0 COMMENT '是否删除',
    `is_test`          TINYINT UNSIGNED DEFAULT 0 COMMENT '是否测试',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户角色';

--
-- 用户管理-用户机构
--
DROP TABLE IF EXISTS `su_user_org`;
CREATE TABLE `su_user_org`
(
    `id`          VARCHAR(32) NOT NULL COMMENT '主键',

    `user_id`     VARCHAR(64) NOT NULL COMMENT '用户ID',
    `user_name`   VARCHAR(32)      DEFAULT '' COMMENT '用户名称',
    `user_code`   VARCHAR(64)      DEFAULT '' COMMENT '用户标示',

    `org_id`      VARCHAR(64) NOT NULL COMMENT '机构ID',
    `org_name`    VARCHAR(32)      DEFAULT '' COMMENT '机构名称',
    `org_code`    VARCHAR(64)      DEFAULT '' COMMENT '机构编码',

    `create_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '创建时间戳',
    `create_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        VARCHAR(64)      DEFAULT '' COMMENT '创建人ID',
    `create_name`      VARCHAR(32)      DEFAULT '' COMMENT '创建人名称',
    `update_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '更新时间戳',
    `update_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`        VARCHAR(64)      DEFAULT '' COMMENT '更新人ID',
    `update_name`      VARCHAR(32)      DEFAULT '' COMMENT '更新人名称',
    `is_del`           TINYINT UNSIGNED DEFAULT 0 COMMENT '是否删除',
    `is_test`          TINYINT UNSIGNED DEFAULT 0 COMMENT '是否测试',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户机构';


--
-- 权限管理-角色
--
DROP TABLE IF EXISTS `su_role`;
CREATE TABLE `su_role`
(
    `id`          VARCHAR(32) NOT NULL COMMENT '主键',

    `name`        VARCHAR(32) NOT NULL COMMENT '名称',
    `code`        VARCHAR(64)      DEFAULT '' COMMENT '编码',

    `remark`      VARCHAR(64)      DEFAULT '' COMMENT '备注',

    `create_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '创建时间戳',
    `create_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        VARCHAR(64)      DEFAULT '' COMMENT '创建人ID',
    `create_name`      VARCHAR(32)      DEFAULT '' COMMENT '创建人名称',
    `update_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '更新时间戳',
    `update_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`        VARCHAR(64)      DEFAULT '' COMMENT '更新人ID',
    `update_name`      VARCHAR(32)      DEFAULT '' COMMENT '更新人名称',
    `is_del`           TINYINT UNSIGNED DEFAULT 0 COMMENT '是否删除',
    `is_test`          TINYINT UNSIGNED DEFAULT 0 COMMENT '是否测试',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='角色';

--
-- 权限管理-资源(菜单&操作)
--
DROP TABLE IF EXISTS `su_resource`;
CREATE TABLE `su_resource`
(
    `id`          VARCHAR(32)      NOT NULL COMMENT '主键',

    `name`        VARCHAR(32)      NOT NULL COMMENT '名称',
    `code`        VARCHAR(32)      DEFAULT '' COMMENT '编码',
    `url`         VARCHAR(128)     DEFAULT '' COMMENT 'URL',
    `icon`        VARCHAR(64)      DEFAULT '' COMMENT '图标',

    `remark`      VARCHAR(64)      DEFAULT '' COMMENT '备注/描述',

    `pid`         VARCHAR(64)      DEFAULT '' COMMENT '父ID',
    `sort`        TINYINT UNSIGNED DEFAULT 0 COMMENT '排序',
    `is_menu`     TINYINT UNSIGNED NOT NULL COMMENT '是否菜单:0=否,1=是',
    `is_leaf`     TINYINT UNSIGNED NOT NULL COMMENT '是否叶子节点:0=否,1=是',

    `create_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '创建时间戳',
    `create_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        VARCHAR(64)      DEFAULT '' COMMENT '创建人ID',
    `create_name`      VARCHAR(32)      DEFAULT '' COMMENT '创建人名称',
    `update_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '更新时间戳',
    `update_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`        VARCHAR(64)      DEFAULT '' COMMENT '更新人ID',
    `update_name`      VARCHAR(32)      DEFAULT '' COMMENT '更新人名称',
    `is_del`           TINYINT UNSIGNED DEFAULT 0 COMMENT '是否删除',
    `is_test`          TINYINT UNSIGNED DEFAULT 0 COMMENT '是否测试',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='菜单';

--
-- 权限管理-角色资源
--
DROP TABLE IF EXISTS `su_role_resource`;
CREATE TABLE `su_role_resource`
(
    `id`            VARCHAR(32) NOT NULL COMMENT '主键',

    `role_id`       VARCHAR(64) NOT NULL COMMENT '角色ID',
    `role_name`     VARCHAR(32)      DEFAULT '' COMMENT '角色名称',
    `role_code`     VARCHAR(64)      DEFAULT '' COMMENT '角色编码',

    `resource_id`   VARCHAR(64) NOT NULL COMMENT '资源ID',
    `resource_name` VARCHAR(32)      DEFAULT '' COMMENT '资源名称',
    `resource_code` VARCHAR(64)      DEFAULT '' COMMENT '资源编码',

    `create_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '创建时间戳',
    `create_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        VARCHAR(64)      DEFAULT '' COMMENT '创建人ID',
    `create_name`      VARCHAR(32)      DEFAULT '' COMMENT '创建人名称',
    `update_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '更新时间戳',
    `update_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`        VARCHAR(64)      DEFAULT '' COMMENT '更新人ID',
    `update_name`      VARCHAR(32)      DEFAULT '' COMMENT '更新人名称',
    `is_del`           TINYINT UNSIGNED DEFAULT 0 COMMENT '是否删除',
    `is_test`          TINYINT UNSIGNED DEFAULT 0 COMMENT '是否测试',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='角色资源';

--
-- 权限管理-机构
--
DROP TABLE IF EXISTS `su_organization`;
CREATE TABLE `su_organization`
(
    `id`          VARCHAR(32)      NOT NULL COMMENT '主键',

    `name`        VARCHAR(32)      NOT NULL COMMENT '名称',
    `code`        VARCHAR(64)      DEFAULT '' COMMENT '编码',
    `address`     VARCHAR(128)     DEFAULT '' COMMENT '地址',

    `type_val`    INT UNSIGNED     DEFAULT 0 COMMENT '机构类型值',
    `type_code`   VARCHAR(16)      DEFAULT '' COMMENT '机构类型编码:字典',

    `sort`        TINYINT UNSIGNED DEFAULT 0 COMMENT '排序',
    `is_leaf`     TINYINT UNSIGNED NOT NULL COMMENT '是否叶子节点:0=否,1=是',
    `pid`         VARCHAR(64)      DEFAULT '' COMMENT '父ID',
    `icon`        VARCHAR(128)     DEFAULT '' COMMENT '图标',

    `remark`      VARCHAR(64)      DEFAULT '' COMMENT '备注/描述',

    `create_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '创建时间戳',
    `create_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        VARCHAR(64)      DEFAULT '' COMMENT '创建人ID',
    `create_name`      VARCHAR(32)      DEFAULT '' COMMENT '创建人名称',
    `update_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '更新时间戳',
    `update_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`        VARCHAR(64)      DEFAULT '' COMMENT '更新人ID',
    `update_name`      VARCHAR(32)      DEFAULT '' COMMENT '更新人名称',
    `is_del`           TINYINT UNSIGNED DEFAULT 0 COMMENT '是否删除',
    `is_test`          TINYINT UNSIGNED DEFAULT 0 COMMENT '是否测试',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='机构';

--
-- 基础数据-日志
--
DROP TABLE IF EXISTS `bd_log`;
CREATE TABLE `bd_log`
(
    `id`            VARCHAR(32) NOT NULL COMMENT '主键',

    `table_name`    VARCHAR(32) NOT NULL COMMENT '表名称',
    `record_id`     VARCHAR(64) NOT NULL COMMENT '记录ID',
    `field_name`    VARCHAR(128)     DEFAULT '' COMMENT '字段名称',

    `log_type_val`  INT UNSIGNED     DEFAULT 0 COMMENT '日志类型值',
    `log_type_code` VARCHAR(16)      DEFAULT '' COMMENT '日志类型编码:字典',

    `opt_type_val`  INT UNSIGNED     DEFAULT 0 COMMENT '操作类型值',
    `opt_type_code` VARCHAR(16)      DEFAULT '' COMMENT '操作类型编码:字典',
    `opt_desc`      VARCHAR(128)     DEFAULT '' COMMENT '操作类型描述',

    `before_value`  VARCHAR(128)     DEFAULT '' COMMENT '操作前值',
    `after_value`   VARCHAR(128)     DEFAULT '' COMMENT '操作后值',

    `remark`        VARCHAR(255)     DEFAULT '' COMMENT '备注',

    `create_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '创建时间戳',
    `create_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        VARCHAR(64)      DEFAULT '' COMMENT '创建人ID',
    `create_name`      VARCHAR(32)      DEFAULT '' COMMENT '创建人名称',
    `update_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '更新时间戳',
    `update_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`        VARCHAR(64)      DEFAULT '' COMMENT '更新人ID',
    `update_name`      VARCHAR(32)      DEFAULT '' COMMENT '更新人名称',
    `is_del`           TINYINT UNSIGNED DEFAULT 0 COMMENT '是否删除',
    `is_test`          TINYINT UNSIGNED DEFAULT 0 COMMENT '是否测试',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='日志';

--
-- 基础数据-字典
--
DROP TABLE IF EXISTS `bd_dictionary`;
CREATE TABLE `bd_dictionary`
(
    `id`          VARCHAR(32)  NOT NULL COMMENT '主键',

    `name`        VARCHAR(32)  NOT NULL COMMENT '名称',
    `code`        VARCHAR(16)  NOT NULL COMMENT '编号',
    `value`       INT UNSIGNED NOT NULL COMMENT '值',

    `type`        VARCHAR(16)      DEFAULT '' COMMENT '类型',
    `type_name`   VARCHAR(32)      DEFAULT '' COMMENT '类型名称',
    `sort`        TINYINT UNSIGNED DEFAULT 0 COMMENT '排序',

    `pid`         VARCHAR(64)      DEFAULT '' COMMENT '父ID',
    `pcode`       VARCHAR(16)      DEFAULT '' COMMENT '父编号',

    `create_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '创建时间戳',
    `create_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        VARCHAR(64)      DEFAULT '' COMMENT '创建人ID',
    `create_name`      VARCHAR(32)      DEFAULT '' COMMENT '创建人名称',
    `update_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '更新时间戳',
    `update_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`        VARCHAR(64)      DEFAULT '' COMMENT '更新人ID',
    `update_name`      VARCHAR(32)      DEFAULT '' COMMENT '更新人名称',
    `is_del`           TINYINT UNSIGNED DEFAULT 0 COMMENT '是否删除',
    `is_test`          TINYINT UNSIGNED DEFAULT 0 COMMENT '是否测试',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='字典';

--
-- 基础数据-文件附件
--
DROP TABLE IF EXISTS `bd_file_attach`;
CREATE TABLE `bd_file_attach`
(
    `id`          VARCHAR(32) NOT NULL COMMENT '主键',

    `table_name`  VARCHAR(32) NOT NULL COMMENT '表名称',
    `record_id`   VARCHAR(64) NOT NULL COMMENT '记录ID',

    `name`        VARCHAR(64)      DEFAULT '' COMMENT '文件原名称',
    `url`         VARCHAR(255)     DEFAULT '' COMMENT '文件URL',
    `icon_url`    VARCHAR(255)     DEFAULT '' COMMENT '文件图标URL',
    `preview_url` VARCHAR(255)     DEFAULT '' COMMENT '文件预览URL',

    `create_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '创建时间戳',
    `create_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        VARCHAR(64)      DEFAULT '' COMMENT '创建人ID',
    `create_name`      VARCHAR(32)      DEFAULT '' COMMENT '创建人名称',
    `update_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '更新时间戳',
    `update_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`        VARCHAR(64)      DEFAULT '' COMMENT '更新人ID',
    `update_name`      VARCHAR(32)      DEFAULT '' COMMENT '更新人名称',
    `is_del`           TINYINT UNSIGNED DEFAULT 0 COMMENT '是否删除',
    `is_test`          TINYINT UNSIGNED DEFAULT 0 COMMENT '是否测试',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文件附件';

--
-- test
--
DROP TABLE IF EXISTS `bd_test`;
CREATE TABLE `bd_test`
(
    `test_name` VARCHAR(32) NOT NULL COMMENT '测试名称',
    `remark`    VARCHAR(255) DEFAULT '' COMMENT '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='测试';

--
-- 银行卡
--
DROP TABLE IF EXISTS `bc_bank_card`;
CREATE TABLE `bc_bank_card`
(
    `id`               VARCHAR(32) NOT NULL COMMENT '主键',

    `user_id`          VARCHAR(64) NOT NULL COMMENT '用户 ID',
    `card_no`          VARCHAR(64) NOT NULL COMMENT '银行卡号',
    `bank_name`        VARCHAR(32) NOT NULL COMMENT '银行名称',
    `bank_code`        VARCHAR(64) NOT NULL COMMENT '银行编码',
    `sub_bank_name`    VARCHAR(32) NOT NULL COMMENT '银行名称',
    `credit_amount`    DECIMAL(19, 4)   DEFAULT NULL COMMENT '授信额度（符合 GAAP 原则）',
    `billing_date`     DATETIME(3)      DEFAULT NULL COMMENT '账单日',
    `repayment_date`   DATETIME(3)      DEFAULT NULL COMMENT '还款日',
    `repayment_offset` INT UNSIGNED     DEFAULT 0 COMMENT '还款日偏移，比如：5，还款日=账单日+5',
    `card_mobile`      VARCHAR(11)      DEFAULT '' COMMENT '手机号',

    `type_val`         INT UNSIGNED     DEFAULT 0 COMMENT '银行卡类型:9000=信用卡,9001=借记卡',
    `type_code`        VARCHAR(16)      DEFAULT '' COMMENT '银行卡类型编码:字典',
    `remark`           VARCHAR(255)     DEFAULT '' COMMENT '备注',

    `create_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '创建时间戳',
    `create_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        VARCHAR(64)      DEFAULT '' COMMENT '创建人ID',
    `create_name`      VARCHAR(32)      DEFAULT '' COMMENT '创建人名称',
    `update_at`        BIGINT UNSIGNED  DEFAULT 0 COMMENT '更新时间戳',
    `update_time`      DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`        VARCHAR(64)      DEFAULT '' COMMENT '更新人ID',
    `update_name`      VARCHAR(32)      DEFAULT '' COMMENT '更新人名称',
    `is_del`           TINYINT UNSIGNED DEFAULT 0 COMMENT '是否删除',
    `is_test`          TINYINT UNSIGNED DEFAULT 0 COMMENT '是否测试',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='银行卡';

ALTER TABLE `su_user`
    ADD INDEX idx_login_name (`login_name`);
ALTER TABLE `su_user`
    ADD INDEX idx_is_del (`is_del`);
ALTER TABLE `su_user_role`
    ADD INDEX idx_user_id (`user_id`);
ALTER TABLE `su_user_role`
    ADD INDEX idx_is_del (`is_del`);
ALTER TABLE `su_user_org`
    ADD INDEX idx_is_del (`is_del`);
ALTER TABLE `su_user_org`
    ADD INDEX idx_user_id (`user_id`);
ALTER TABLE `su_role`
    ADD INDEX idx_is_del (`is_del`);
ALTER TABLE `su_resource`
    ADD INDEX idx_is_del (`is_del`);
ALTER TABLE `su_resource`
    ADD INDEX idx_url (`url`);
ALTER TABLE `su_role_resource`
    ADD INDEX idx_is_del (`is_del`);
ALTER TABLE `su_organization`
    ADD INDEX idx_is_del (`is_del`);
ALTER TABLE `bd_log`
    ADD INDEX idx_is_del (`is_del`);
ALTER TABLE `bd_dictionary`
    ADD INDEX idx_code (`code`);
ALTER TABLE `bd_dictionary`
    ADD INDEX idx_type (`type`);
ALTER TABLE `bd_dictionary`
    ADD INDEX idx_is_del (`is_del`);
ALTER TABLE `bd_file_attach`
    ADD INDEX idx_is_del (`is_del`);
ALTER TABLE `bc_bank_card`
    ADD INDEX idx_is_del (`is_del`);

--
-- 初始化:字典
--
TRUNCATE `bd_dictionary`;
INSERT INTO `bd_dictionary` (`id`, `name`, `code`, `value`, `type`, `type_name`, `sort`, `pid`, `pcode`, `create_at`,
                             `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('110771', '启用', 'QY', 1000, 'YHZT', '用户状态', 0, '0', '', 1513563952466, '0', 'SYSTEM',
        1513563952466, '0', 'SYSTEM', 0, 0);
INSERT INTO `bd_dictionary` (`id`, `name`, `code`, `value`, `type`, `type_name`, `sort`, `pid`, `pcode`, `create_at`,
                             `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('110772', '停用', 'TY', 1001, 'YHZT', '用户状态', 0, '0', '', 1513563952490, '0', 'SYSTEM',
        1513563952490, '0', 'SYSTEM', 0, 0);
INSERT INTO `bd_dictionary` (`id`, `name`, `code`, `value`, `type`, `type_name`, `sort`, `pid`, `pcode`, `create_at`,
                             `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('110773', '其他', 'QT', 2000, 'JGLX', '机构类型', 0, '0', '', 1513563952491, '0', 'SYSTEM',
        1513563952491, '0', 'SYSTEM', 0, 0);
INSERT INTO `bd_dictionary` (`id`, `name`, `code`, `value`, `type`, `type_name`, `sort`, `pid`, `pcode`, `create_at`,
                             `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('110774', '登录登出', 'DLDC', 3000, 'RZLX', '日志类型', 0, '0', '', 1513563952491, '0',
        'SYSTEM', 1513563952491, '0', 'SYSTEM', 0, 0);
INSERT INTO `bd_dictionary` (`id`, `name`, `code`, `value`, `type`, `type_name`, `sort`, `pid`, `pcode`, `create_at`,
                             `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('110775', '登录', 'DL', 3001, 'CZLX', '操作类型', 0, '110774', '',
        1513563952491, '0', 'SYSTEM', 1513563952491, '0', 'SYSTEM', 0, 0);
INSERT INTO `bd_dictionary` (`id`, `name`, `code`, `value`, `type`, `type_name`, `sort`, `pid`, `pcode`, `create_at`,
                             `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('110776', '登出', 'DC', 3002, 'CZLX', '操作类型', 0, '110774', '',
        1513563952491, '0', 'SYSTEM', 1513563952491, '0', 'SYSTEM', 0, 0);
INSERT INTO `bd_dictionary` (`id`, `name`, `code`, `value`, `type`, `type_name`, `sort`, `pid`, `pcode`, `create_at`,
                             `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('110777', '启用停用', 'QYTY', 4000, 'RZLX', '日志类型', 0, '0', '', 1513563952491, '0',
        'SYSTEM', 1513563952491, '0', 'SYSTEM', 0, 0);
INSERT INTO `bd_dictionary` (`id`, `name`, `code`, `value`, `type`, `type_name`, `sort`, `pid`, `pcode`, `create_at`,
                             `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('110778', '启用', 'QY', 4001, 'CZLX', '操作类型', 0, '110777', '',
        1513563952492, '0', 'SYSTEM', 1513563952492, '0', 'SYSTEM', 0, 0);
INSERT INTO `bd_dictionary` (`id`, `name`, `code`, `value`, `type`, `type_name`, `sort`, `pid`, `pcode`, `create_at`,
                             `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('110779', '停用', 'TY', 4002, 'CZLX', '操作类型', 0, '110777', '',
        1513563952492, '0', 'SYSTEM', 1513563952492, '0', 'SYSTEM', 0, 0);

--
-- 初始化:菜单资源+角色资源
--
TRUNCATE `su_resource`;
TRUNCATE `su_role_resource`;
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('120771', '用户管理', 'YHGL', '', 'ios-people', '', '', 0, 1, 0, 1513146159132, '0',
        'SYSTEM', 1513146159132, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('190771', '170771', '超级管理员', 'CJGLY',
        '120771', '用户管理', 'YHGL', 1513146159148, '0', 'SYSTEM', 1513146159148, '0', 'SYSTEM',
        0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('120772', '人员管理', 'YHGL:RYGL', '/user', 'ios-body', '',
        '120771', 0, 1, 1, 1513146159148, '0', 'SYSTEM', 1513146159148, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('190772', '170771', '超级管理员', 'CJGLY',
        '120772', '人员管理', 'YHGL:RYGL', 1513146159148, '0', 'SYSTEM', 1513146159148, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('120773', '查询', 'YHGL:RYGL:CX', '', '', '', '120772', 0, 0,
        1, 1513146159149, '0', 'SYSTEM', 1513146159149, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('190773', '170771', '超级管理员', 'CJGLY',
        '120773', '查询', 'YHGL:RYGL:CX', 1513146159149, '0', 'SYSTEM', 1513146159149, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('120774', '查看', 'YHGL:RYGL:CK', '', '', '', '120772', 0, 0,
        1, 1513146159149, '0', 'SYSTEM', 1513146159149, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('190774', '170771', '超级管理员', 'CJGLY',
        '120774', '查看', 'YHGL:RYGL:CK', 1513146159149, '0', 'SYSTEM', 1513146159149, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('120775', '新增', 'YHGL:RYGL:XZ', '', '', '', '120772', 0, 0,
        1, 1513146159149, '0', 'SYSTEM', 1513146159149, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('190775', '170771', '超级管理员', 'CJGLY',
        '120775', '新增', 'YHGL:RYGL:XZ', 1513146159149, '0', 'SYSTEM', 1513146159149, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('120776', '编辑', 'YHGL:RYGL:BJ', '', '', '', '120772', 0, 0,
        1, 1513146159150, '0', 'SYSTEM', 1513146159150, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('190776', '170771', '超级管理员', 'CJGLY',
        '120776', '编辑', 'YHGL:RYGL:BJ', 1513146159150, '0', 'SYSTEM', 1513146159150, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('120777', '删除', 'YHGL:RYGL:SC', '', '', '', '120772', 0, 0,
        1, 1513146159150, '0', 'SYSTEM', 1513146159150, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('190777', '170771', '超级管理员', 'CJGLY',
        '120777', '删除', 'YHGL:RYGL:SC', 1513146159150, '0', 'SYSTEM', 1513146159150, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('120778', '角色配置', 'YHGL:RYGL:JSPZ', '', '', '', '120772', 0,
        0, 1, 1513146159150, '0', 'SYSTEM', 1513146159150, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('190778', '170771', '超级管理员', 'CJGLY',
        '120778', '角色配置', 'YHGL:RYGL:JSPZ', 1513146159151, '0', 'SYSTEM', 1513146159151, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('120779', '机构配置', 'YHGL:RYGL:JGPZ', '', '', '', '120772', 0,
        0, 1, 1513146159151, '0', 'SYSTEM', 1513146159151, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('190779', '170771', '超级管理员', 'CJGLY',
        '120779', '机构配置', 'YHGL:RYGL:JGPZ', 1513146159151, '0', 'SYSTEM', 1513146159151, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027710', '角色管理', 'YHGL:JSGL', '/role', 'ios-person', '',
        '120771', 0, 1, 1, 1513146159151, '0', 'SYSTEM', 1513146159151, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('20027710', '170771', '超级管理员', 'CJGLY',
        '18027710', '角色管理', 'YHGL:JSGL', 1513146159151, '0', 'SYSTEM', 1513146159151, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027711', '查询', 'YHGL:JSGL:CX', '', '', '', '18027710', 0, 0,
        1, 1513146159152, '0', 'SYSTEM', 1513146159152, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('20027711', '170771', '超级管理员', 'CJGLY',
        '18027711', '查询', 'YHGL:JSGL:CX', 1513146159152, '0', 'SYSTEM', 1513146159152, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027712', '查看', 'YHGL:JSGL:CK', '', '', '', '18027710', 0, 0,
        1, 1513146159152, '0', 'SYSTEM', 1513146159152, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('20027712', '170771', '超级管理员', 'CJGLY',
        '18027712', '查看', 'YHGL:JSGL:CK', 1513146159152, '0', 'SYSTEM', 1513146159152, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027713', '新增', 'YHGL:JSGL:XZ', '', '', '', '18027710', 0, 0,
        1, 1513146159152, '0', 'SYSTEM', 1513146159152, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('20027713', '170771', '超级管理员', 'CJGLY',
        '18027713', '新增', 'YHGL:JSGL:XZ', 1513146159153, '0', 'SYSTEM', 1513146159153, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027714', '编辑', 'YHGL:JSGL:BJ', '', '', '', '18027710', 0, 0,
        1, 1513146159153, '0', 'SYSTEM', 1513146159153, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('20027714', '170771', '超级管理员', 'CJGLY',
        '18027714', '编辑', 'YHGL:JSGL:BJ', 1513146159153, '0', 'SYSTEM', 1513146159153, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027715', '删除', 'YHGL:JSGL:SC', '', '', '', '18027710', 0, 0,
        1, 1513146159153, '0', 'SYSTEM', 1513146159153, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('20027715', '170771', '超级管理员', 'CJGLY',
        '18027715', '删除', 'YHGL:JSGL:SC', 1513146159153, '0', 'SYSTEM', 1513146159153, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027716', '权限配置', 'YHGL:JSGL:QXPZ', '', '', '', '18027710', 0,
        0, 1, 1513146159154, '0', 'SYSTEM', 1513146159154, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('20027716', '170771', '超级管理员', 'CJGLY',
        '18027716', '权限配置', 'YHGL:JSGL:QXPZ', 1513146159154, '0', 'SYSTEM', 1513146159154, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027717', '组织机构', 'YHGL:ZZJG', '/organization', 'ios-people', '',
        '120771', 0, 1, 1, 1513146159154, '0', 'SYSTEM', 1513146159154, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('20027717', '170771', '超级管理员', 'CJGLY',
        '18027717', '组织机构', 'YHGL:ZZJG', 1513146159154, '0', 'SYSTEM', 1513146159154, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027718', '查询', 'YHGL:ZZJG:CX', '', '', '', '18027717', 0, 0,
        1, 1513146159154, '0', 'SYSTEM', 1513146159154, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('20027718', '170771', '超级管理员', 'CJGLY',
        '18027718', '查询', 'YHGL:ZZJG:CX', 1513146159154, '0', 'SYSTEM', 1513146159154, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027719', '查看', 'YHGL:ZZJG:CK', '', '', '', '18027717', 0, 0,
        1, 1513146159155, '0', 'SYSTEM', 1513146159155, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('20027719', '170771', '超级管理员', 'CJGLY',
        '18027719', '查看', 'YHGL:ZZJG:CK', 1513146159155, '0', 'SYSTEM', 1513146159155, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027720', '新增', 'YHGL:ZZJG:XZ', '', '', '', '18027717', 0, 0,
        1, 1513146159155, '0', 'SYSTEM', 1513146159155, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('20027720', '170771', '超级管理员', 'CJGLY',
        '18027720', '新增', 'YHGL:ZZJG:XZ', 1513146159155, '0', 'SYSTEM', 1513146159155, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027721', '编辑', 'YHGL:ZZJG:BJ', '', '', '', '18027717', 0, 0,
        1, 1513146159155, '0', 'SYSTEM', 1513146159155, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('20027721', '170771', '超级管理员', 'CJGLY',
        '18027721', '编辑', 'YHGL:ZZJG:BJ', 1513146159156, '0', 'SYSTEM', 1513146159156, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027722', '删除', 'YHGL:ZZJG:SC', '', '', '', '18027717', 0, 0,
        1, 1513146159156, '0', 'SYSTEM', 1513146159156, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027722', '170771', '超级管理员', 'CJGLY',
        '18027722', '删除', 'YHGL:ZZJG:SC', 1513146159156, '0', 'SYSTEM', 1513146159156, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027723', '菜单资源', 'YHGL:CDZY', '/resource', 'android-menu', '',
        '120771', 0, 1, 1, 1513146159156, '0', 'SYSTEM', 1513146159156, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027723', '170771', '超级管理员', 'CJGLY',
        '18027723', '菜单资源', 'YHGL:CDZY', 1513146159156, '0', 'SYSTEM', 1513146159156, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027724', '查询', 'YHGL:CDZY:CX', '', '', '', '18027723', 0, 0,
        1, 1513146159157, '0', 'SYSTEM', 1513146159157, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027724', '170771', '超级管理员', 'CJGLY',
        '18027724', '查询', 'YHGL:CDZY:CX', 1513146159157, '0', 'SYSTEM', 1513146159157, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027725', '查看', 'YHGL:CDZY:CK', '', '', '', '18027723', 0, 0,
        1, 1513146159157, '0', 'SYSTEM', 1513146159157, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027725', '170771', '超级管理员', 'CJGLY',
        '18027725', '查看', 'YHGL:CDZY:CK', 1513146159157, '0', 'SYSTEM', 1513146159157, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027726', '新增', 'YHGL:CDZY:XZ', '', '', '', '18027723', 0, 0,
        1, 1513146159157, '0', 'SYSTEM', 1513146159157, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027726', '170771', '超级管理员', 'CJGLY',
        '18027726', '新增', 'YHGL:CDZY:XZ', 1513146159158, '0', 'SYSTEM', 1513146159158, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027727', '编辑', 'YHGL:CDZY:BJ', '', '', '', '18027723', 0, 0,
        1, 1513146159158, '0', 'SYSTEM', 1513146159158, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027727', '170771', '超级管理员', 'CJGLY',
        '18027727', '编辑', 'YHGL:CDZY:BJ', 1513146159158, '0', 'SYSTEM', 1513146159158, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027728', '删除', 'YHGL:CDZY:SC', '', '', '', '18027723', 0, 0,
        1, 1513146159158, '0', 'SYSTEM', 1513146159158, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027728', '170771', '超级管理员', 'CJGLY',
        '18027728', '删除', 'YHGL:CDZY:SC', 1513146159158, '0', 'SYSTEM', 1513146159158, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027729', '基础数据', 'JCSJ', '', 'settings', '', '', 0, 1, 0, 1513146159158, '0',
        'SYSTEM', 1513146159158, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027729', '170771', '超级管理员', 'CJGLY',
        '18027729', '基础数据', 'JCSJ', 1513146159159, '0', 'SYSTEM', 1513146159159, '0', 'SYSTEM',
        0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027730', '公共码表', 'JCSJ:GGMB', '/dictionary', 'ios-book', '',
        '18027729', 0, 1, 1, 1513146159159, '0', 'SYSTEM', 1513146159159, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027730', '170771', '超级管理员', 'CJGLY',
        '18027730', '公共码表', 'JCSJ:GGMB', 1513146159159, '0', 'SYSTEM', 1513146159159, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027731', '查询', 'JCSJ:GGMB:CX', '', '', '', '18027730', 0, 0,
        1, 1513146159159, '0', 'SYSTEM', 1513146159159, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027731', '170771', '超级管理员', 'CJGLY',
        '18027731', '查询', 'JCSJ:GGMB:CX', 1513146159160, '0', 'SYSTEM', 1513146159160, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027732', '查看', 'JCSJ:GGMB:CK', '', '', '', '18027730', 0, 0,
        1, 1513146159160, '0', 'SYSTEM', 1513146159160, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027732', '170771', '超级管理员', 'CJGLY',
        '18027732', '查看', 'JCSJ:GGMB:CK', 1513146159160, '0', 'SYSTEM', 1513146159160, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027733', '新增', 'JCSJ:GGMB:XZ', '', '', '', '18027730', 0, 0,
        1, 1513146159161, '0', 'SYSTEM', 1513146159161, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027733', '170771', '超级管理员', 'CJGLY',
        '18027733', '新增', 'JCSJ:GGMB:XZ', 1513146159161, '0', 'SYSTEM', 1513146159161, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027734', '编辑', 'JCSJ:GGMB:BJ', '', '', '', '18027730', 0, 0,
        1, 1513146159161, '0', 'SYSTEM', 1513146159161, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027734', '170771', '超级管理员', 'CJGLY',
        '18027734', '编辑', 'JCSJ:GGMB:BJ', 1513146159161, '0', 'SYSTEM', 1513146159161, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027735', '删除', 'JCSJ:GGMB:SC', '', '', '', '18027730', 0, 0,
        1, 1513146159162, '0', 'SYSTEM', 1513146159162, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027735', '170771', '超级管理员', 'CJGLY',
        '18027735', '删除', 'JCSJ:GGMB:SC', 1513146159162, '0', 'SYSTEM', 1513146159162, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027736', '文件管理', 'JCSJ:WJGL', '/fileAttach', 'ios-folder', '',
        '18027729', 0, 1, 1, 1513146159162, '0', 'SYSTEM', 1513146159162, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027736', '170771', '超级管理员', 'CJGLY',
        '18027736', '文件管理', 'JCSJ:WJGL', 1513146159162, '0', 'SYSTEM', 1513146159162, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027737', '查询', 'JCSJ:WJGL:CX', '', '', '', '18027736', 0, 0,
        1, 1513146159163, '0', 'SYSTEM', 1513146159163, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027737', '170771', '超级管理员', 'CJGLY',
        '18027737', '查询', 'JCSJ:WJGL:CX', 1513146159163, '0', 'SYSTEM', 1513146159163, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027738', '查看', 'JCSJ:WJGL:CK', '', '', '', '18027736', 0, 0,
        1, 1513146159163, '0', 'SYSTEM', 1513146159163, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027738', '170771', '超级管理员', 'CJGLY',
        '18027738', '查看', 'JCSJ:WJGL:CK', 1513146159163, '0', 'SYSTEM', 1513146159163, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027739', '新增', 'JCSJ:WJGL:XZ', '', '', '', '18027736', 0, 0,
        1, 1513146159164, '0', 'SYSTEM', 1513146159164, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027739', '170771', '超级管理员', 'CJGLY',
        '18027739', '新增', 'JCSJ:WJGL:XZ', 1513146159164, '0', 'SYSTEM', 1513146159164, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027740', '编辑', 'JCSJ:WJGL:BJ', '', '', '', '18027736', 0, 0,
        1, 1513146159164, '0', 'SYSTEM', 1513146159164, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027740', '170771', '超级管理员', 'CJGLY',
        '18027740', '编辑', 'JCSJ:WJGL:BJ', 1513146159164, '0', 'SYSTEM', 1513146159164, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027741', '删除', 'JCSJ:WJGL:SC', '', '', '', '18027736', 0, 0,
        1, 1513146159165, '0', 'SYSTEM', 1513146159165, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027741', '170771', '超级管理员', 'CJGLY',
        '18027741', '删除', 'JCSJ:WJGL:SC', 1513146159165, '0', 'SYSTEM', 1513146159165, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027742', '代码平台', 'DMPT', '', 'code-working', '', '', 0, 1, 0, 1513146159165, '0',
        'SYSTEM', 1513146159165, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027742', '170771', '超级管理员', 'CJGLY',
        '18027742', '代码平台', 'DMPT', 1513146159165, '0', 'SYSTEM', 1513146159165, '0', 'SYSTEM',
        0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027743', '接口文档', 'DMPT:JKWD', '/api', 'usb', '', '18027742',
        0, 1, 1, 1513146159165, '0', 'SYSTEM', 1513146159165, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027743', '170771', '超级管理员', 'CJGLY',
        '18027743', '接口文档', 'DMPT:JKWD', 1513146159165, '0', 'SYSTEM', 1513146159165, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027744', '查询', 'DMPT:JKWD:CX', '', '', '', '18027743', 0, 0,
        1, 1513146159165, '0', 'SYSTEM', 1513146159165, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027744', '170771', '超级管理员', 'CJGLY',
        '18027744', '查询', 'DMPT:JKWD:CX', 1513146159166, '0', 'SYSTEM', 1513146159166, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027745', '查看', 'DMPT:JKWD:CK', '', '', '', '18027743', 0, 0,
        1, 1513146159166, '0', 'SYSTEM', 1513146159166, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027745', '170771', '超级管理员', 'CJGLY',
        '18027745', '查看', 'DMPT:JKWD:CK', 1513146159166, '0', 'SYSTEM', 1513146159166, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027746', '新增', 'DMPT:JKWD:XZ', '', '', '', '18027743', 0, 0,
        1, 1513146159166, '0', 'SYSTEM', 1513146159166, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027746', '170771', '超级管理员', 'CJGLY',
        '18027746', '新增', 'DMPT:JKWD:XZ', 1513146159167, '0', 'SYSTEM', 1513146159167, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027747', '编辑', 'DMPT:JKWD:BJ', '', '', '', '18027743', 0, 0,
        1, 1513146159167, '0', 'SYSTEM', 1513146159167, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027747', '170771', '超级管理员', 'CJGLY',
        '18027747', '编辑', 'DMPT:JKWD:BJ', 1513146159167, '0', 'SYSTEM', 1513146159167, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027748', '删除', 'DMPT:JKWD:SC', '', '', '', '18027743', 0, 0,
        1, 1513146159167, '0', 'SYSTEM', 1513146159167, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027748', '170771', '超级管理员', 'CJGLY',
        '18027748', '删除', 'DMPT:JKWD:SC', 1513146159167, '0', 'SYSTEM', 1513146159167, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027749', '代码生成', 'DMPT:DMSC', '/code', 'code-download', '',
        '18027742', 0, 1, 1, 1513146159167, '0', 'SYSTEM', 1513146159167, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027749', '170771', '超级管理员', 'CJGLY',
        '18027749', '代码生成', 'DMPT:DMSC', 1513146159167, '0', 'SYSTEM', 1513146159167, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027750', '查询', 'DMPT:DMSC:CX', '', '', '', '18027749', 0, 0,
        1, 1513146159168, '0', 'SYSTEM', 1513146159168, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027750', '170771', '超级管理员', 'CJGLY',
        '18027750', '查询', 'DMPT:DMSC:CX', 1513146159168, '0', 'SYSTEM', 1513146159168, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027751', '查看', 'DMPT:DMSC:CK', '', '', '', '18027749', 0, 0,
        1, 1513146159168, '0', 'SYSTEM', 1513146159168, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027751', '170771', '超级管理员', 'CJGLY',
        '18027751', '查看', 'DMPT:DMSC:CK', 1513146159168, '0', 'SYSTEM', 1513146159168, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027752', '新增', 'DMPT:DMSC:XZ', '', '', '', '18027749', 0, 0,
        1, 1513146159168, '0', 'SYSTEM', 1513146159168, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027752', '170771', '超级管理员', 'CJGLY',
        '18027752', '新增', 'DMPT:DMSC:XZ', 1513146159168, '0', 'SYSTEM', 1513146159168, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027753', '编辑', 'DMPT:DMSC:BJ', '', '', '', '18027749', 0, 0,
        1, 1513146159169, '0', 'SYSTEM', 1513146159169, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027753', '170771', '超级管理员', 'CJGLY',
        '18027753', '编辑', 'DMPT:DMSC:BJ', 1513146159169, '0', 'SYSTEM', 1513146159169, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027754', '删除', 'DMPT:DMSC:SC', '', '', '', '18027749', 0, 0,
        1, 1513146159169, '0', 'SYSTEM', 1513146159169, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027754', '170771', '超级管理员', 'CJGLY',
        '18027754', '删除', 'DMPT:DMSC:SC', 1513146159169, '0', 'SYSTEM', 1513146159169, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027755', '日志监控', 'RZJK', '', 'monitor', '', '', 0, 1, 0, 1513146159169, '0',
        'SYSTEM', 1513146159169, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027755', '170771', '超级管理员', 'CJGLY',
        '18027755', '日志监控', 'RZJK', 1513146159169, '0', 'SYSTEM', 1513146159169, '0', 'SYSTEM',
        0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027756', '操作日志', 'RZJK:CZRZ', '/log', 'ios-paper', '',
        '18027755', 0, 1, 1, 1513146159169, '0', 'SYSTEM', 1513146159169, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027756', '170771', '超级管理员', 'CJGLY',
        '18027756', '操作日志', 'RZJK:CZRZ', 1513146159170, '0', 'SYSTEM', 1513146159170, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027757', '查询', 'RZJK:CZRZ:CX', '', '', '', '18027756', 0, 0,
        1, 1513146159170, '0', 'SYSTEM', 1513146159170, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027757', '170771', '超级管理员', 'CJGLY',
        '18027757', '查询', 'RZJK:CZRZ:CX', 1513146159170, '0', 'SYSTEM', 1513146159170, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027758', '查看', 'RZJK:CZRZ:CK', '', '', '', '18027756', 0, 0,
        1, 1513146159170, '0', 'SYSTEM', 1513146159170, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027758', '170771', '超级管理员', 'CJGLY',
        '18027758', '查看', 'RZJK:CZRZ:CK', 1513146159170, '0', 'SYSTEM', 1513146159170, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027759', '新增', 'RZJK:CZRZ:XZ', '', '', '', '18027756', 0, 0,
        1, 1513146159170, '0', 'SYSTEM', 1513146159170, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027759', '170771', '超级管理员', 'CJGLY',
        '18027759', '新增', 'RZJK:CZRZ:XZ', 1513146159171, '0', 'SYSTEM', 1513146159171, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027760', '编辑', 'RZJK:CZRZ:BJ', '', '', '', '18027756', 0, 0,
        1, 1513146159171, '0', 'SYSTEM', 1513146159171, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027760', '170771', '超级管理员', 'CJGLY',
        '18027760', '编辑', 'RZJK:CZRZ:BJ', 1513146159171, '0', 'SYSTEM', 1513146159171, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027761', '删除', 'RZJK:CZRZ:SC', '', '', '', '18027756', 0, 0,
        1, 1513146159171, '0', 'SYSTEM', 1513146159171, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027761', '170771', '超级管理员', 'CJGLY',
        '18027761', '删除', 'RZJK:CZRZ:SC', 1513146159171, '0', 'SYSTEM', 1513146159171, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027762', '数据监控', 'RZJK:SJJK', '/druid', 'ios-analytics', '',
        '18027755', 0, 1, 1, 1513146159172, '0', 'SYSTEM', 1513146159172, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027762', '170771', '超级管理员', 'CJGLY',
        '18027762', '数据监控', 'RZJK:SJJK', 1513146159172, '0', 'SYSTEM', 1513146159172, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027763', '查询', 'RZJK:SJJK:CX', '', '', '', '18027762', 0, 0,
        1, 1513146159172, '0', 'SYSTEM', 1513146159172, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027763', '170771', '超级管理员', 'CJGLY',
        '18027763', '查询', 'RZJK:SJJK:CX', 1513146159172, '0', 'SYSTEM', 1513146159172, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027764', '查看', 'RZJK:SJJK:CK', '', '', '', '18027762', 0, 0,
        1, 1513146159172, '0', 'SYSTEM', 1513146159172, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027764', '170771', '超级管理员', 'CJGLY',
        '18027764', '查看', 'RZJK:SJJK:CK', 1513146159172, '0', 'SYSTEM', 1513146159172, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027765', '新增', 'RZJK:SJJK:XZ', '', '', '', '18027762', 0, 0,
        1, 1513146159173, '0', 'SYSTEM', 1513146159173, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027765', '170771', '超级管理员', 'CJGLY',
        '18027765', '新增', 'RZJK:SJJK:XZ', 1513146159173, '0', 'SYSTEM', 1513146159173, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027766', '编辑', 'RZJK:SJJK:BJ', '', '', '', '18027762', 0, 0,
        1, 1513146159173, '0', 'SYSTEM', 1513146159173, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027766', '170771', '超级管理员', 'CJGLY',
        '18027766', '编辑', 'RZJK:SJJK:BJ', 1513146159173, '0', 'SYSTEM', 1513146159173, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027767', '删除', 'RZJK:SJJK:SC', '', '', '', '18027762', 0, 0,
        1, 1513146159173, '0', 'SYSTEM', 1513146159173, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027767', '170771', '超级管理员', 'CJGLY',
        '18027767', '删除', 'RZJK:SJJK:SC', 1513146159174, '0', 'SYSTEM', 1513146159174, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027768', '接口监控', 'RZJK:JKJK', '/monitor', 'usb', '',
        '18027755', 0, 1, 1, 1513146159174, '0', 'SYSTEM', 1513146159174, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027768', '170771', '超级管理员', 'CJGLY',
        '18027768', '接口监控', 'RZJK:JKJK', 1513146159174, '0', 'SYSTEM', 1513146159174, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027769', '查询', 'RZJK:JKJK:CX', '', '', '', '18027768', 0, 0,
        1, 1513146159174, '0', 'SYSTEM', 1513146159174, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027769', '170771', '超级管理员', 'CJGLY',
        '18027769', '查询', 'RZJK:JKJK:CX', 1513146159174, '0', 'SYSTEM', 1513146159174, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027770', '查看', 'RZJK:JKJK:CK', '', '', '', '18027768', 0, 0,
        1, 1513146159174, '0', 'SYSTEM', 1513146159174, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027770', '170771', '超级管理员', 'CJGLY',
        '18027770', '查看', 'RZJK:JKJK:CK', 1513146159174, '0', 'SYSTEM', 1513146159174, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027771', '新增', 'RZJK:JKJK:XZ', '', '', '', '18027768', 0, 0,
        1, 1513146159175, '0', 'SYSTEM', 1513146159175, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027771', '170771', '超级管理员', 'CJGLY',
        '18027771', '新增', 'RZJK:JKJK:XZ', 1513146159175, '0', 'SYSTEM', 1513146159175, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027772', '编辑', 'RZJK:JKJK:BJ', '', '', '', '18027768', 0, 0,
        1, 1513146159175, '0', 'SYSTEM', 1513146159175, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027772', '170771', '超级管理员', 'CJGLY',
        '18027772', '编辑', 'RZJK:JKJK:BJ', 1513146159175, '0', 'SYSTEM', 1513146159175, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_resource` (`id`, `name`, `code`, `url`, `icon`, `remark`, `pid`, `sort`, `is_menu`, `is_leaf`,
                           `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`,
                           `is_test`)
VALUES ('18027773', '删除', 'RZJK:JKJK:SC', '', '', '', '18027768', 0, 0,
        1, 1513146159175, '0', 'SYSTEM', 1513146159175, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_role_resource` (`id`, `role_id`, `role_name`, `role_code`, `resource_id`, `resource_name`,
                                `resource_code`, `create_at`, `create_by`, `create_name`, `update_at`, `update_by`,
                                `update_name`, `is_del`, `is_test`)
VALUES ('13027773', '170771', '超级管理员', 'CJGLY',
        '18027773', '删除', 'RZJK:JKJK:SC', 1513146159175, '0', 'SYSTEM', 1513146159175, '0',
        'SYSTEM', 0, 0);

--
-- 初始化:角色
--
TRUNCATE `su_role`;
INSERT INTO `su_role` (`id`, `name`, `code`, `remark`, `create_at`, `create_by`, `create_name`, `update_at`,
                       `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('170771', '超级管理员', 'CJGLY', '谨慎操作', 1491805287470, '0', 'SYSTEM', 1491805287470, '0',
        'SYSTEM', 0, 0);

--
-- 初始化:机构
--
TRUNCATE `su_organization`;
INSERT INTO `su_organization` (`id`, `name`, `code`, `type_val`, `type_code`, `sort`, `is_leaf`, `pid`, `remark`,
                               `create_at`, `create_by`, `create_name`, `update_at`, `update_by`, `update_name`,
                               `is_del`, `is_test`)
VALUES ('160771', '超级管理组', 'CJGLZ', 2000, 'QT', 0, 1, '', '谨慎操作', 1491805287470, '0',
        'SYSTEM', 1491805287470, '0', 'SYSTEM', 0, 0);

--
-- 初始化:用户+用户机构+用户角色
--
TRUNCATE `su_user`;
INSERT INTO `su_user` (`id`, `name`, `login_name`, `code`, `pwd`, `salt`, `mobile`, `status_val`, `status_code`,
                       `last_access_time`, `total_amount`, `remark`, `create_at`, `create_by`, `create_name`,
                       `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('140771', 'admin', 'admin', 'ADMIN', '7d61f71f34b0305aabc5d1cdd9d2a777', 'abc',
        '13011111111', 1000, 'QY', '2021-07-22 10:22:22', 999999.00, '谨慎操作', 1491805287470, '0', 'SYSTEM',
        1491805287470, '0', 'SYSTEM', 0, 0);
INSERT INTO `su_user` (`id`, `name`, `login_name`, `code`, `pwd`, `salt`, `mobile`, `status_val`, `status_code`,
                       `last_access_time`, `total_amount`, `remark`, `create_at`, `create_by`, `create_name`,
                       `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('140772', 'super_admin', 'super_admin', 'SUPER_ADMIN',
        '7d61f71f34b0305aabc5d1cdd9d2a777', 'abc', '13022222222', 1000, 'QY', '2021-07-22 10:22:22', 999999.00, '谨慎操作',
        1491805287470, '0', 'SYSTEM', 1491805287470, '0', 'SYSTEM', 0, 0);

TRUNCATE `su_user_org`;
INSERT INTO `su_user_org` (`id`, `user_id`, `user_name`, `user_code`, `org_id`, `org_name`, `org_code`, `create_at`,
                           `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('150771', '140771', 'admin', 'ADMIN',
        '160771', '超级管理组', 'CJGLZ', 1491805287470, '0', 'SYSTEM', 1491805287470, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_user_org` (`id`, `user_id`, `user_name`, `user_code`, `org_id`, `org_name`, `org_code`, `create_at`,
                           `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('150772', '140772', 'super_admin', 'SUPER_ADMIN',
        '160771', '超级管理组', 'CJGLZ', 1491805287470, '0', 'SYSTEM', 1491805287470, '0',
        'SYSTEM', 0, 0);

TRUNCATE `su_user_role`;
INSERT INTO `su_user_role` (`id`, `user_id`, `user_name`, `user_code`, `role_id`, `role_name`, `role_code`, `create_at`,
                            `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('210771', '140771', 'admin', 'ADMIN',
        '170771', '超级管理员', 'CJGLY', 1491805287470, '0', 'SYSTEM', 1491805287470, '0',
        'SYSTEM', 0, 0);
INSERT INTO `su_user_role` (`id`, `user_id`, `user_name`, `user_code`, `role_id`, `role_name`, `role_code`, `create_at`,
                            `create_by`, `create_name`, `update_at`, `update_by`, `update_name`, `is_del`, `is_test`)
VALUES ('210772', '140772', 'super_admin', 'SUPER_ADMIN',
        '170771', '超级管理员', 'CJGLY', 1491805287470, '0', 'SYSTEM', 1491805287470, '0',
        'SYSTEM', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
