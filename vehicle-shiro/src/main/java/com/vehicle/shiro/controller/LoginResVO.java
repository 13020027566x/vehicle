package com.vehicle.shiro.controller;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录 DTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/03/16 15:36
 */
@Data
public class LoginResVO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户Token
     */
    private String token;

    /**
     * 访问权限
     */
    private String access;

    /**
     * 用户头像
     */
    private String avatar;
}
