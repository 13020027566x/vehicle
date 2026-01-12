package com.vehicle.dao.roleresource.po;

import com.finhub.framework.mybatis.po.BasePO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色资源
 *
 * @author Mickey
 * @version 1.0
 * @since 2018/06/06 14:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "su_role_resource", resultMap = "RoleResourceResultMap")
public class RoleResourcePO extends BasePO {

    private static final long serialVersionUID = 5409185459234711691L;

    public static final String DB_TABLE_NAME = "su_role_resource";

    public static final String DB_COL_ROLE_ID = "role_id";

    public static final String DB_COL_ROLE_NAME = "role_name";

    public static final String DB_COL_ROLE_CODE = "role_code";

    public static final String DB_COL_RESOURCE_ID = "resource_id";

    public static final String DB_COL_RESOURCE_NAME = "resource_name";

    public static final String DB_COL_RESOURCE_CODE = "resource_code";


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

    /**
     * 资源ID
     */
    private String resourceId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源编码
     */
    private String resourceCode;

}
