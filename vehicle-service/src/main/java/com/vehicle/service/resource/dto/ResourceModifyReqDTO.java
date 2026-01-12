package com.vehicle.service.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 菜单 修改 ReqDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceModifyReqDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * URL
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注/描述
     */
    private String remark;

    /**
     * 父ID
     */
    private String pid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否菜单:0=否,1=是
     */
    private Integer isMenu;

    /**
     * 是否叶子节点:0=否,1=是
     */
    private Integer isLeaf;
}
