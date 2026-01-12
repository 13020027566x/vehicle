package com.vehicle.service.userorg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户机构 删除 ReqDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrgRemoveReqDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 主键
     */
    private String id;

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

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 创建人ID
     */
    private String createBy;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 更新人ID
     */
    private String updateBy;

    /**
     * 更新人名称
     */
    private String updateName;

    /**
     * 是否删除
     */
    private Integer isDel;

    /**
     * 是否测试
     */
    private Integer isTest;

}
