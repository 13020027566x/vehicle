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
public class UserPageRoleResDTO implements Serializable {

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
     * 备注
     */
    private String remark;

    /**
     * 更新时间
     */
    private String updateAt;

    /**
     * 更新人
     */
    private String updateName;

    /**
     * 是否勾选
     */
    private Boolean checked;
}
