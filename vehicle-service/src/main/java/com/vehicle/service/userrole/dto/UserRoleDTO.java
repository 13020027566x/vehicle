package com.vehicle.service.userrole.dto;

import com.finhub.framework.common.dto.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户角色 DTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;
}
