package com.finhub.framework.web.async.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : liuwei
 * @date : 2022/1/24
 * @desc :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeoutResult {
    /**
     * 超时code码
     */
    private int code;

    /**
     * 超时时间信息
     */
    private String msg;
}
