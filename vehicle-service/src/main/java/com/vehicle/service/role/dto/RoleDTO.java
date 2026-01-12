package com.vehicle.service.role.dto;

import com.finhub.framework.common.dto.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色 DTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 备注
     */
    private String remark;
}
