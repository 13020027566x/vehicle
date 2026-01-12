package com.finhub.framework.common.config;

import com.finhub.framework.common.lock.service.RedisServiceTest;
import com.finhub.framework.common.lock.service.RedisServiceTestImpl;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : liuwei
 * @date : 2021/10/22
 * @desc :
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedissonClient getRedisson(){
         Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://10.200.15.11:6379")
            .setPassword("Finhub@2021")
            .setDatabase(15);
        return Redisson.create(config);
    }

    @Bean
    public RedisServiceTest redisServiceTest() {
        return new RedisServiceTestImpl();
    }
}
