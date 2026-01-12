package com.finhub.framework.xxljob.configuration;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cn.hutool.core.text.CharSequenceUtil.isNotBlank;

/**
 * Xxl Job 定时任务
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "vehicle.xxljob.enabled", matchIfMissing = true)
public class XxlJobAutoloadConfiguration {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appname;

    @Value("${xxl.job.executor.port:9999}")
    private int port;

    @Value("${xxl.job.accessToken:}")
    private String accessToken;

    @Value("${xxl.job.executor.address:}")
    private String address;

    @Value("${xxl.job.executor.ip:}")
    private String ip;

    @Value("${xxl.job.executor.logpath:}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays:3}")
    private int logRetentionDays;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

        if (isNotBlank(accessToken)) {
            xxlJobSpringExecutor.setAccessToken(accessToken);
        }

        if (isNotBlank(address)) {
            xxlJobSpringExecutor.setAddress(address);
        }

        if (isNotBlank(ip)) {
            xxlJobSpringExecutor.setIp(ip);
        }

        if (isNotBlank(logPath)) {
            xxlJobSpringExecutor.setLogPath(logPath);
        }

        log.info(">>>>>>>>>>> xxl-job executor config init success. [adminAddresses='{}', appname='{}', port={}, logRetentionDays={}]", adminAddresses, appname, port, logRetentionDays);
        return xxlJobSpringExecutor;
    }
}
