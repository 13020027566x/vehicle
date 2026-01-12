package com.vehicle.service.userorg.dto;

import com.finhub.framework.common.dto.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户机构 DTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrgDTO extends BaseDTO implements Serializable {

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
     * 用户标示
     */
    private String userCode;

    /**
     * 机构ID
     */
    private String orgId;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构编码
     */
    private String orgCode;
}
