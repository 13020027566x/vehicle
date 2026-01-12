package com.vehicle.dao.log.po;

import com.finhub.framework.mybatis.po.BasePO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 日志
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bd_log", resultMap = "LogResultMap")
public class LogPO extends BasePO {

    private static final long serialVersionUID = 5409185459234711691L;

    public static final String DB_TABLE_NAME = "bd_log";

    public static final String DB_COL_TABLE_NAME = "table_name";

    public static final String DB_COL_RECORD_ID = "record_id";

    public static final String DB_COL_FIELD_NAME = "field_name";

    public static final String DB_COL_LOG_TYPE_VAL = "log_type_val";

    public static final String DB_COL_LOG_TYPE_CODE = "log_type_code";

    public static final String DB_COL_OPT_TYPE_VAL = "opt_type_val";

    public static final String DB_COL_OPT_TYPE_CODE = "opt_type_code";

    public static final String DB_COL_OPT_DESC = "opt_desc";

    public static final String DB_COL_BEFORE_VALUE = "before_value";

    public static final String DB_COL_AFTER_VALUE = "after_value";

    public static final String DB_COL_REMARK = "remark";


    /**
     * 表名称
     */
    private String tableName;

    /**
     * 记录ID
     */
    private String recordId;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 日志类型值
     */
    private Integer logTypeVal;

    /**
     * 日志类型编码:字典
     */
    private String logTypeCode;

    /**
     * 操作类型值
     */
    private Integer optTypeVal;

    /**
     * 操作类型编码:字典
     */
    private String optTypeCode;

    /**
     * 操作类型描述
     */
    private String optDesc;

    /**
     * 操作前值
     */
    private String beforeValue;

    /**
     * 操作后值
     */
    private String afterValue;

    /**
     * 备注
     */
    private String remark;

}
