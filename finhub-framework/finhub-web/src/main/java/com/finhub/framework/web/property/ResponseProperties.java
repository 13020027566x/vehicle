package com.finhub.framework.web.property;

import com.finhub.framework.core.str.StrConstants;
import com.finhub.framework.exception.constant.enums.ReturnCodeEnum;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.finhub.framework.core.number.NumberConstants.N_ZERO;
import static com.finhub.framework.core.str.StrConstants.S_EMPTY;

/**
 * @author : liuwei
 * @date : 2022/7/26
 * @desc :
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "vehicle.response")
public class ResponseProperties {

    public static ResponseProperties me() {
        try {
            return SpringUtil.getBean(ResponseProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new ResponseProperties();
        }
    }

    /**
     * 是否启用 ResponseProperties 配置，默认为 false
     */
    private Boolean enable = false;

    /**
     * 响应码
     */
    private Integer successCode = ReturnCodeEnum.RC200.getCode();

    /**
     * 提示信息
     */
    private String successMsg = StrConstants.S_SUCCESS;

    /**
     * 错误类型
     */
    private Integer successType = N_ZERO;

    /**
     * App 弹框提示标题
     */
    private String successTitle = S_EMPTY;

    /**
     * 总条数
     */
    private Integer successCount = N_ZERO;

    /**
     * 响应码
     */
    private Integer failCode = ReturnCodeEnum.RC500.getCode();

    /**
     * 提示信息
     */
    private String failMsg = StrConstants.S_FAIL;

    /**
     * 响应结构体中 data 是否填充数据对象，默认不填充
     */
    private Boolean fillDataObjectEnable = false;
}
