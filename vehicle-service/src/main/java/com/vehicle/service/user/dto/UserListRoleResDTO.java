package com.vehicle.service.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户角色 分页 ResDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListRoleResDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 角色ID
     */
    private String roleId;
}
