package com.vehicle.dao.user.po;

import com.finhub.framework.mybatis.po.BasePO;
import com.finhub.framework.safety.typehandler.SafetyColumnTypeHandler;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户
 *
 * @author Mickey
 * @version 1.0
 * @since 2018/06/06 14:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "su_user", resultMap = "UserResultMap")
public class UserPO extends BasePO {

    private static final long serialVersionUID = 5409185459234711691L;

    public static final String DB_TABLE_NAME = "su_user";

    public static final String DB_COL_NAME = "name";

    public static final String DB_COL_LOGIN_NAME = "login_name";

    public static final String DB_COL_CODE = "code";

    public static final String DB_COL_PWD = "pwd";

    public static final String DB_COL_SALT = "salt";

    public static final String DB_COL_MOBILE = "mobile";

    public static final String DB_COL_STATUS_VAL = "status_val";

    public static final String DB_COL_STATUS_CODE = "status_code";

    public static final String DB_COL_LAST_ACCESS_TIME = "last_access_time";

    public static final String DB_COL_TOTAL_AMOUNT = "total_amount";

    public static final String DB_COL_REMARK = "remark";


    /**
     * 名称
     */
    private String name;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 编码
     */
    private String code;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 手机号
     */
    @TableField(typeHandler = SafetyColumnTypeHandler.class)
    private String mobile;

    /**
     * 用户状态值:1000=启用,1001=停用
     */
    private Integer statusVal;

    /**
     * 用户状态编码:字典
     */
    private String statusCode;

    /**
     * 最近访问时间
     */
    private Date lastAccessTime;

    /**
     * 账户余额（符合 GAAP 原则）
     */
    private BigDecimal totalAmount;

    /**
     * 备注
     */
    private String remark;

}
