package com.vehicle.service.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 字典 分页 ReqDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryPageReqDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

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
     * 父字典名称
     */
    private String pname;

    /**
     * 父字典编号
     */
    private String pcode;

    /**
     * 父字典值
     */
    private Integer pvalue;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 更新起始时间
     */
    private String updateStartAt;

    /**
     * 更新结束时间
     */
    private String updateEndAt;
}
