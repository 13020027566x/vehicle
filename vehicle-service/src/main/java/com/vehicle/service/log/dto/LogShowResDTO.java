package com.vehicle.service.log.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 日志 详情 ResDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogShowResDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 主键
     */
    private String id;

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

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 创建人ID
     */
    private String createBy;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 更新人ID
     */
    private String updateBy;

    /**
     * 更新人名称
     */
    private String updateName;

    /**
     * 是否删除
     */
    private Integer isDel;

    /**
     * 是否测试
     */
    private Integer isTest;

}
