package com.vehicle.service.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 用户 详情 ResDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListOrganizationResDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 主键
     */
    private String id;

    /**
     * 机构名称
     */
    private String title;

    /**
     * 是否展开
     */
    private Boolean expand;

    /**
     * 是否选中
     */
    private Boolean selected;

    /**
     * 是否勾选
     */
    private Boolean checked;

    /**
     * 是否不可用
     */
    private Boolean disabled;

    /**
     * 父ID
     */
    private String pid;

    /**
     * 子机构
     */
    private List<UserListOrganizationResDTO> children;
}
