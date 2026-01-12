package com.finhub.framework.nacos.property;

import cn.hutool.extra.spring.SpringUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "finhub.nacos")
public class NacosBootProperties {

    private static final Logger log = LoggerFactory.getLogger(NacosBootProperties.class);

    public static NacosBootProperties me() {
        try {
            return SpringUtil.getBean(NacosBootProperties.class);
        } catch (Exception e) {
            log.warn("SpringUtil get bean fail, return new instance.", e);  // 修复语法
            return new NacosBootProperties();
        }
    }

    @Valid
    @NotNull(message = "nacos.config cannot be null")
    private Config config = new Config();  // 添加默认初始化


    @Data
    public static class Config {  // 改为 public static
        @NotNull(message = "nacos.config.server-addr cannot be null")
        private String serverAddr;

        @Valid
        @NotNull(message = "nacos.config.bootstrap cannot be null")
        private Bootstrap bootstrap = new Bootstrap();  // 添加默认初始化

        @NotNull(message = "nacos.config.data-ids cannot be null")
        private String dataIds;

        @Pattern(regexp = "YAML", message = "nacos.config.type must be YAML")
        private String type = "YAML";  // 添加默认值

        @NotNull(message = "nacos.config.group cannot be null")
        private String group = "DEFAULT_GROUP";  // 添加默认值

        @NotNull(message = "nacos.config.namespace cannot be null")
        private String namespace = "";  // 添加默认值（空字符串表示公共命名空间）

        @AssertTrue(message = "nacos.config.auto-refresh must be true")
        private Boolean autoRefresh = true;  // 添加默认值
    }


    @Data
    public static class Bootstrap {  // 改为 public static
        @AssertTrue(message = "nacos.config.bootstrap.enable must be true")
        private Boolean enable = true;  // 添加默认值

        @AssertTrue(message = "nacos.config.bootstrap.log-enable must be true")
        private Boolean logEnable = true;  // 添加默认值
    }
}
