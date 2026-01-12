package com.vehicle.service.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户 列表 ResDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListResDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 编码
     */
    private String code;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户状态值:1000=启用,1001=停用
     */
    private Integer statusVal;

    /**
     * 用户状态编码:字典
     */
    private String statusCode;

    /**
     * 最近访问时间
     */
    private Date lastAccessTime;

    /**
     * 账户余额（符合 GAAP 原则）
     */
    private BigDecimal totalAmount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 创建人ID
     */
    private String createBy;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 更新人ID
     */
    private String updateBy;

    /**
     * 更新人名称
     */
    private String updateName;

    /**
     * 是否删除
     */
    private Integer isDel;

    /**
     * 是否测试
     */
    private Integer isTest;

}
