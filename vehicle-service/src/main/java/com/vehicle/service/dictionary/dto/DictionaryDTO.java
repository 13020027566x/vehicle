package com.vehicle.service.dictionary.dto;

import com.finhub.framework.common.dto.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 字典 DTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

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
