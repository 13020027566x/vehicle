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
public class LoginReqVO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 是否记住
     */
    private Integer rememberMe;
}
