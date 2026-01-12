package com.finhub.framework.web.property;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * ConfigurationProperties 相关链接
 * （1）https://blog.csdn.net/yusimiao/article/details/97622666
 *
 * @author : liuwei
 * @date : 2021/11/25
 * @desc :
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "vehicle.filter")
public class FilterProperties {

    public static FilterProperties me() {
        try {
            return SpringUtil.getBean(FilterProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);
            return new FilterProperties();
        }
    }

    private Set<String> xssIncludeUrls;

    private Set<String> xssExcludeUrls;

    private Set<String> sqlIncludeUrls;

    private Set<String> sqlExcludeUrls;

    private boolean xssEnabled = true;

    private boolean sqlEnabled = true;

    private boolean swiftXssEnabled = false;

    private boolean swiftSqlEnabled = false;

    private Set<String> ignoreHeaders;

    private Set<String> ignoreParameters;

    private List<FilterRuler> filterRulers;


    @Data
    public static class FilterRuler {

        /**
         * 正则匹配
         */
        private String regex;

        /**
         * 替换字符串
         */
        private String replacement;
    }
}
