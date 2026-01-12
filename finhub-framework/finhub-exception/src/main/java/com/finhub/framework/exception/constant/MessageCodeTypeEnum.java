package com.finhub.framework.exception.constant;

import com.finhub.framework.exception.constant.enums.ArgumentResponseEnum;
import com.finhub.framework.exception.constant.enums.BusinessResponseEnum;

import java.io.Serializable;

/**
 * 兼容老项目特殊code转化type码的枚举工具
 * https://wiki.fenbeijinfu.com/pages/viewpage.action?pageId=3873910
 *
 * @author liuwei
 */
public enum MessageCodeTypeEnum implements Serializable {

    /**
     * 没有登录
     */
    NO_AUTH(401, 1),

    /**
     * 没有刷新
     */
    NO_REFRESH(402, 2),

    /**
     * 没有权限
     */
    NO_PERMISSION(403, 7),

    /**
     * 参数错误
     */
    EXCEPTION(500, 6),

    /**
     * 参数错误
     */
    ILLEGAL_ARGUMENT(600, 6),


    /**
     * 业务提示
     */
    FAIL(666, 7),

    /**
     * 参数缺失
     */
    ARGUMENT_COMMON_ERROR(ArgumentResponseEnum.COMMON_ERROR.getCode(), 6),

    /**
     * 参数缺失
     */
    ARGUMENT_MISSING_PARAM(ArgumentResponseEnum.COMMON_ERROR.getCode(), 6),

    /**
     * 参数无效
     */
    ARGUMENT_INVALID_ERROR(ArgumentResponseEnum.INVALID_ERROR.getCode(), 6),

    /**
     * 通用业务异常
     */
    BUSINESS_COMMON_ERROR(BusinessResponseEnum.COMMON_ERROR.getCode(), 7),

    ;
    /**
     * @See FinhubMessageCode.class
     */
    int code;

    /**
     * @See FinhubMessageType.class
     */
    int type;

    MessageCodeTypeEnum(int code, int type) {
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public int getType() {
        return type;
    }

    /**
     * 根据code码获取相应type类型
     *
     * @param code
     * @return
     */
    public static int getTypeByCode(int code) {

        return getTypeByCode(code, FAIL.getType());

    }

    /**
     * 根据code码获取相应type类型
     *
     * @param code
     * @param defaultType
     * @return
     */
    public static Integer getTypeByCode(int code, Integer defaultType) {

        MessageCodeTypeEnum[] values = values();
        for (MessageCodeTypeEnum messageCodeTypeEnum : values) {
            if (messageCodeTypeEnum.getCode() == code) {
                return messageCodeTypeEnum.getType();
            }
        }

        return defaultType;
    }

}
