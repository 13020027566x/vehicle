package com.vehicle.service.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 字典 分页 ResDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryPageResDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 字典主键
     */
    private String id;

    /**
     * 字典父id
     */
    private String pid;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 父字典名称
     */
    private String pname;

    /**
     * 字典编号
     */
    private String code;

    /**
     * 父字典编号
     */
    private String pcode;

    /**
     * 字典值
     */
    private Integer value;

    /**
     * 父字典值
     */
    private Integer pvalue;

    /**
     * 类型
     */
    private String type;

    /**
     * 字典类型名称
     */
    private String typeName;

    private String updateAt;
}
