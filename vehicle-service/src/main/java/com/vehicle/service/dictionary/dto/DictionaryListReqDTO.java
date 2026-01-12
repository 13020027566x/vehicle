package com.vehicle.service.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 字典 列表 ReqDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryListReqDTO implements Serializable {

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
     * 字典编号
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
}
