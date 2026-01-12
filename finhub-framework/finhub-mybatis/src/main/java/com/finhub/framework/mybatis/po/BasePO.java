package com.finhub.framework.mybatis.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * BasePO
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
public class BasePO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    public static final String DB_COL_ID = "id";

    public static final String DB_COL_IS_DEL = "is_del";

    public static final String DB_COL_IS_TEST = "is_test";

    public static final String DB_COL_CREATE_AT = "create_at";

    public static final String DB_COL_CREATE_TIME = "create_time";

    public static final String DB_COL_CREATE_BY = "create_by";

    public static final String DB_COL_CREATE_NAME = "create_name";

    public static final String DB_COL_UPDATE_AT = "update_at";

    public static final String DB_COL_UPDATE_TIME = "update_time";

    public static final String DB_COL_UPDATE_BY = "update_by";

    public static final String DB_COL_UPDATE_NAME = "update_name";

    public static final String PROPERTY_ID = "id";

    public static final String PROPERTY_IS_DEL = "isDel";

    public static final String PROPERTY_IS_TEST = "isTest";

    public static final String PROPERTY_CREATE_AT = "createAt";

    public static final String PROPERTY_CREATE_TIME = "createTime";

    public static final String PROPERTY_CREATE_BY = "createBy";

    public static final String PROPERTY_CREATE_NAME = "createName";

    public static final String PROPERTY_UPDATE_AT = "updateAt";

    public static final String PROPERTY_UPDATE_TIME = "updateTime";

    public static final String PROPERTY_UPDATE_BY = "updateBy";

    public static final String PROPERTY_UPDATE_NAME = "updateName";

    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 是否删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isDel;
    /**
     * 是否测试
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isTest;
    /**
     * 创建时间戳
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createAt;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 创建者ID
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 创建者名称
     */
    @TableField(fill = FieldFill.INSERT)
    private String createName;
    /**
     * 更新时间戳
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateAt;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 更新者ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    /**
     * 更新者名称
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateName;
}
