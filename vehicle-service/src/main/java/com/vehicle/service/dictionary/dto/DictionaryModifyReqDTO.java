package com.vehicle.service.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 字典 修改 ReqDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryModifyReqDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 字典id
     */
    private String id;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典编号
     */
    private String code;

    /**
     * 字典值
     */
    private Integer value;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 字典类型名称
     */
    private String typeName;

    /**
     * 父字典ID
     */
    private String pid;

    /**
     * 父字典编号
     */
    private String pcode;
}
