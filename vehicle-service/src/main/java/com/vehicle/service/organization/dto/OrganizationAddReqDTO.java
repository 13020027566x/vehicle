package com.vehicle.service.organization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 机构 添加 ReqDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationAddReqDTO implements Serializable {

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
     * 排序
     */
    private Integer sort;

    /**
     * 是否叶子节点:0=否,1=是
     */
    private Integer isLeaf;

    /**
     * 父ID
     */
    private String pid;

    /**
     * 备注/描述
     */
    private String remark;
}
