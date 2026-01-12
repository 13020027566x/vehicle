package com.vehicle.service.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户 详情 ResDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserShowResDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 主键
     */
    private String id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户状态值:1000=启用,1001=停用
     */
    private Integer statusVal;

    /**
     * 用户状态编码
     */
    private String statusCode;

    /**
     * 用户状态
     */
    private String statusName;

    /**
     * 加密盐
     */
    private String salt;

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
     * 角色名称
     */
    private String roleNames;

    /**
     * 机构名称
     */
    private String orgNames;
}
