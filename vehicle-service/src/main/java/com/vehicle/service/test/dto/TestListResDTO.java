package com.vehicle.service.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 测试 列表 ResDTO
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021-09-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestListResDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 测试名称
     */
    private String testName;

    /**
     * 备注
     */
    private String remark;

}
