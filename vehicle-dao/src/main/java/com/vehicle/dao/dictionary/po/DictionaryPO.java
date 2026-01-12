package com.vehicle.dao.dictionary.po;

import com.finhub.framework.mybatis.po.BasePO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典
 *
 * @author Mickey
 * @version 1.0
 * @since 2018/06/06 14:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bd_dictionary", resultMap = "DictionaryResultMap")
public class DictionaryPO extends BasePO {

    private static final long serialVersionUID = 5409185459234711691L;

    public static final String DB_TABLE_NAME = "bd_dictionary";

    public static final String DB_COL_NAME = "name";

    public static final String DB_COL_CODE = "code";

    public static final String DB_COL_VALUE = "value";

    public static final String DB_COL_TYPE = "type";

    public static final String DB_COL_TYPE_NAME = "type_name";

    public static final String DB_COL_SORT = "sort";

    public static final String DB_COL_PID = "pid";

    public static final String DB_COL_PCODE = "pcode";


    /**
     * 名称
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    /**
     * 值
     */
    private Integer value;

    /**
     * 类型
     */
    private String type;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父ID
     */
    private String pid;

    /**
     * 父编号
     */
    private String pcode;

}
