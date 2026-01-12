package com.finhub.framework.quartz.configuration;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Calendar;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "vehicle.quartz.enabled", matchIfMissing = true)
public class QuartzAutoloadConfiguration {

    /**
     * 创建 quartz 数据源的配置对象
     * 读取 spring.datasource.quartz 配置到 DataSourceProperties 对象
     */
    @Bean(name = "quartzDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.quartz")
    public DataSourceProperties quartzDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 创建 quartz 数据源
     */
    @Bean(name = "quartzDataSource")
    public DataSource quartzDataSource() {
        // 获得 DataSourceProperties 对象
        DataSourceProperties dataSourceProperties = this.quartzDataSourceProperties();
        // 创建 HikariDataSource 对象
        return createHikariDataSource(dataSourceProperties);
    }

    private static HikariDataSource createHikariDataSource(DataSourceProperties dataSourceProperties) {
        // 创建 HikariDataSource 对象
        HikariDataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        // 设置 线程池名
        if (StringUtils.hasText(dataSourceProperties.getName())) {
            dataSource.setPoolName(dataSourceProperties.getName());
        }
        return dataSource;
    }

    @Bean
    public SchedulerFactoryBean quartzScheduler(@Qualifier("quartzDataSource") DataSource quartzDataSource, QuartzProperties properties,
        ObjectProvider<SchedulerFactoryBeanCustomizer> customizers, ObjectProvider<JobDetail> jobDetails,
        Map<String, Calendar> calendars, ObjectProvider<Trigger> triggers, ApplicationContext applicationContext) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        schedulerFactoryBean.setJobFactory(jobFactory);
        if (properties.getSchedulerName() != null) {
            schedulerFactoryBean.setSchedulerName(properties.getSchedulerName());
        }
        // # Quartz 是否自动启动
        schedulerFactoryBean.setAutoStartup(true);
        // # 延迟 N 秒启动
        schedulerFactoryBean.setStartupDelay(0);
        // # 应用关闭时，是否等待定时任务执行完成。默认为 false ，建议设置为 true
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
        // # 是否覆盖已有 Job 的配置
        schedulerFactoryBean.setOverwriteExistingJobs(false);
        if (!properties.getProperties().isEmpty()) {
            schedulerFactoryBean.setQuartzProperties(asProperties(properties.getProperties()));
        }
        schedulerFactoryBean.setJobDetails(jobDetails.orderedStream().toArray(JobDetail[]::new));
        schedulerFactoryBean.setCalendars(calendars);
        schedulerFactoryBean.setTriggers(triggers.orderedStream().toArray(Trigger[]::new));
        // # 手动设置数据源 quartzDataSource
        schedulerFactoryBean.setDataSource(quartzDataSource);
        customizers.orderedStream().forEach((customizer) -> customizer.customize(schedulerFactoryBean));
        return schedulerFactoryBean;
    }

    private Properties asProperties(Map<String, String> source) {
        Properties properties = new Properties();
        properties.putAll(source);
        return properties;
    }
}
