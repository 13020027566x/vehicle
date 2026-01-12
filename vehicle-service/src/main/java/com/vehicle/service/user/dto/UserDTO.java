package com.vehicle.service.user.dto;

import com.finhub.framework.common.dto.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户 DTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

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
}
