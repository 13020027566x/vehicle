package com.vehicle.service.log.dto;

import com.finhub.framework.common.dto.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 日志 DTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

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
