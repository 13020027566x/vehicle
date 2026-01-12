package com.vehicle.service.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户 分页 ResDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPageResDTO implements Serializable {

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
     * 登录名
     */
    private String loginName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户状态名称
     */
    private String statusName;

    /**
     * 更新时间
     */
    private String updateAt;

    /**
     * 更新人
     */
    private String updateName;
}
