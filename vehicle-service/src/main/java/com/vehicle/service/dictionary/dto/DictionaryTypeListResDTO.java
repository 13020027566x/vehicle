package com.vehicle.service.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 字典 DTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/03/28 15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryTypeListResDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 字典类型名称
     */
    private String name;

    /**
     * 字典类型编号
     */
    private String code;
}
