package com.vehicle.dao.userorg.po;

import com.finhub.framework.mybatis.po.BasePO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户机构
 *
 * @author Mickey
 * @version 1.0
 * @since 2018/06/06 14:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "su_user_org", resultMap = "UserOrgResultMap")
public class UserOrgPO extends BasePO {

    private static final long serialVersionUID = 5409185459234711691L;

    public static final String DB_TABLE_NAME = "su_user_org";

    public static final String DB_COL_USER_ID = "user_id";

    public static final String DB_COL_USER_NAME = "user_name";

    public static final String DB_COL_USER_CODE = "user_code";

    public static final String DB_COL_ORG_ID = "org_id";

    public static final String DB_COL_ORG_NAME = "org_name";

    public static final String DB_COL_ORG_CODE = "org_code";


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

}
