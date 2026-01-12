package com.finhub.framework.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * BaseDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

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
    private String id;
    /**
     * 是否删除
     */
    private Integer isDel;
    /**
     * 是否测试
     */
    private Integer isTest;
    /**
     * 创建时间戳
     */
    private Long createAt;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者ID
     */
    private String createBy;
    /**
     * 创建者名称
     */
    private String createName;
    /**
     * 更新时间戳
     */
    private Long updateAt;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新者ID
     */
    private String updateBy;
    /**
     * 更新者名称
     */
    private String updateName;
}
