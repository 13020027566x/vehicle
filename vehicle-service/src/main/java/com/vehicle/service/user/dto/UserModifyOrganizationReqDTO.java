package com.vehicle.service.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 用户 修改 ReqDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModifyOrganizationReqDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 机构ID集合
     */
    private List<String> orgIds;
}
