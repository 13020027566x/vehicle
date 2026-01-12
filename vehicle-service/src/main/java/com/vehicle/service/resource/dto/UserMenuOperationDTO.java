package com.vehicle.service.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class UserMenuOperationDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 操作ID
     */
    private String id;

    /**
     * 操作名称
     */
    private String name;

    /**
     * 操作编码
     */
    private String code;

    /**
     * 操作图标
     */
    private String icon;
}
