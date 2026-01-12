package com.finhub.framework.logback.definer;

import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.PropertyDefiner;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.finhub.framework.logback.constant.LogConstants.S_COMMA;

/**
 * 日志属性解析器
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/02/10 17:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LogPropertyDefiner extends ContextAwareBase implements PropertyDefiner {

    private String argument;

    private Integer index;

    private String defaultValue;

    @Override
    public String getPropertyValue() {
        String value = System.getProperty(argument);

        if (StrUtil.isBlank(value)) {
            return defaultValue;
        }

        String[] args = value.split(S_COMMA);
        if (args.length <= index) {
            return defaultValue;
        }

        return args[index];
    }
}
