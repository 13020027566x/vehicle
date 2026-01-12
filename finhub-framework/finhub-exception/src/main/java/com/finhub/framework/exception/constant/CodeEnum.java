package com.finhub.framework.exception.constant;

/**
 * 异常状态码
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public interface CodeEnum {

    /**
     * 信息码
     */
    int getCode();

    /**
     * 信息描述
     */
    String getMessage();

    /**
     * 信息提示类型
     * <p>
     * 默认为 0 ，不指定任何提示类型
     */
    default int getType() {
        return 0;
    }
}
