package com.vehicle.service.role.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色 分页 ReqDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePageReqDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 名称
     */
    private String name;

    /**
     * 更新起始时间
     */
    private String updateStartAt;

    /**
     * 更新结束时间
     */
    private String updateEndAt;
}
