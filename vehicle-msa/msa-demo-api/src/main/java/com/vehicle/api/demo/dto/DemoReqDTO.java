package com.vehicle.api.demo.dto;

import com.finhub.framework.validator.constant.GenderEnum;
import com.finhub.framework.validator.intarray.InEnum;

import lombok.Data;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

/**
 * 用户信息 DTO
 */
@Data
public class DemoReqDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    @NotNull(message = "{DemoReqDTO.name.NotNull}")
    private String name;

    /**
     * 性别
     */
    @InEnum(value = GenderEnum.class, message = "性别必须是 {value}")
    private Integer gender;

    public Integer getId() {
        return id;
    }

    public DemoReqDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DemoReqDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public DemoReqDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }
}
