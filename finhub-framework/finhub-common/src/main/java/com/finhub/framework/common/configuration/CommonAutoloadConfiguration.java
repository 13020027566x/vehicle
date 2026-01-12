package com.finhub.framework.common.configuration;

import com.finhub.framework.common.domain.DomainCostLogAspect;
import com.finhub.framework.common.graceful.GracefulShutdownListener;
import com.finhub.framework.common.graceful.GracefulStartupListener;
import com.finhub.framework.common.lock.aspect.DistributeLockAspect;
import com.finhub.framework.common.lock.aspect.MultiDistributeLockAspect;
import com.finhub.framework.common.manager.aspect.ManagerCostLogAspect;
import com.finhub.framework.common.property.ZooKeeperProperties;
import com.finhub.framework.common.service.aspect.ServiceCostLogAspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午4:19
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "vehicle.common.enabled", matchIfMissing = true)
public class CommonAutoloadConfiguration {

    @Bean
    @ConditionalOnProperty(name = "zookeeper.url")
    ZooKeeperProperties zooKeeperProperties() {
        return new ZooKeeperProperties();
    }

    @Bean
    @ConditionalOnProperty(name = "zookeeper.url")
    public CuratorFramework curatorFramework(ZooKeeperProperties zooKeeperProperties) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zooKeeperProperties.getTimeout(), zooKeeperProperties.getRetry());
        CuratorFramework client = CuratorFrameworkFactory.newClient(zooKeeperProperties.getUrl(), retryPolicy);
        client.start();
        return client;
    }

    /**
     * 优雅上线
     *
     * @return
     */
    @Bean
    public GracefulStartupListener gracefulStartupListener() {
        return new GracefulStartupListener();
    }

    /**
     * 优雅下线
     *
     * @return
     */
    @Bean
    public GracefulShutdownListener gracefulShutdownListener() {
        return new GracefulShutdownListener();
    }

    /**
     * 切面配置：Service层方法执行日志
     *
     * @return
     */
    @Bean
    public ServiceCostLogAspect serviceCostLogAspect() {
        return new ServiceCostLogAspect();
    }

    /**
     * 切面配置：Manager层方法执行日志
     *
     * @return
     */
    @Bean
    public ManagerCostLogAspect managerCostLogAspect() {
        return new ManagerCostLogAspect();
    }

    /**
     * 切面配置：Domain层方法执行日志
     *
     * @return
     */
    @Bean
    public DomainCostLogAspect domainCostLogAspect() {
        return new DomainCostLogAspect();
    }

    /**
     * 分布式锁切面：单条件锁
     *
     * @param redissonClient
     * @return
     */
    @Bean
    @ConditionalOnBean(RedissonClient.class)
    public DistributeLockAspect distributeLockAspect(RedissonClient redissonClient) {
        return new DistributeLockAspect(redissonClient);
    }

    /**
     * 分布式锁切面：多条件锁
     *
     * @param redissonClient
     * @return
     */
    @Bean
    @ConditionalOnBean(RedissonClient.class)
    public MultiDistributeLockAspect multiDistributeLockAspect(RedissonClient redissonClient) {
        return new MultiDistributeLockAspect(redissonClient);
    }
}
