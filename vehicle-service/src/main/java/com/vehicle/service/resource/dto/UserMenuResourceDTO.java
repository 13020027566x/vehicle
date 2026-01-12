package com.vehicle.service.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单 DTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/03/28 15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMenuResourceDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 菜单ID
     */
    private String id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单编码
     */
    private String code;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单URL
     */
    private String url;


    private List<UserMenuResourceDTO> childrens;
}
