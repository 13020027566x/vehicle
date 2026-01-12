package com.finhub.framework.exception.constant;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/07/15 19:02
 */
@Data
@AllArgsConstructor
public class BaseCodeEnum implements CodeEnum, Serializable {

    private static final long serialVersionUID = 8247610319171014183L;

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
