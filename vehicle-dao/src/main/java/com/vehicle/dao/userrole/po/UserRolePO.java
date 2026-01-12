package com.vehicle.dao.userrole.po;

import com.finhub.framework.mybatis.po.BasePO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色
 *
 * @author Mickey
 * @version 1.0
 * @since 2018/06/06 14:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "su_user_role", resultMap = "UserRoleResultMap")
public class UserRolePO extends BasePO {

    private static final long serialVersionUID = 5409185459234711691L;

    public static final String DB_TABLE_NAME = "su_user_role";

    public static final String DB_COL_USER_ID = "user_id";

    public static final String DB_COL_USER_NAME = "user_name";

    public static final String DB_COL_USER_CODE = "user_code";

    public static final String DB_COL_ROLE_ID = "role_id";

    public static final String DB_COL_ROLE_NAME = "role_name";

    public static final String DB_COL_ROLE_CODE = "role_code";


    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

}
