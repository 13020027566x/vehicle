package com.vehicle.provider.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息 DTO
 */
@Data
public class DemoDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;
}
