package com.vehicle.service.organization.dto;

import com.finhub.framework.common.dto.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 机构 DTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO extends BaseDTO implements Serializable {

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
     * 地址
     */
    private String address;

    /**
     * 机构类型值
     */
    private Integer typeVal;

    /**
     * 机构类型编码:字典
     */
    private String typeCode;

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
     * 图标
     */
    private String icon;

    /**
     * 备注/描述
     */
    private String remark;
}
