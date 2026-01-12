package com.finhub.framework.cache.configuration;

import com.finhub.framework.cache.config.CacheConfig;
import com.finhub.framework.cache.property.RedisProperties;
import com.finhub.framework.core.Func;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午4:19
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "vehicle.cache.enabled", matchIfMissing = true)
public class RedisTemplateConfiguration {

    final RedisProperties redisProperties;

    public RedisTemplateConfiguration(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public LettuceClientConfiguration lettuceClientConfiguration() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getMaxWaitMillis());
        jedisPoolConfig.setTestOnBorrow(redisProperties.isTestOnBorrow());
        return LettucePoolingClientConfiguration.builder().poolConfig(jedisPoolConfig).build();
    }

    public RedisSentinelConfiguration redisSentinelConfiguration() {
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        redisSentinelConfiguration.setMaster("mymaster");
        redisSentinelConfiguration.addSentinel(new RedisNode("192.168.13.111", 30011));
        redisSentinelConfiguration.addSentinel(new RedisNode("192.168.13.112", 30011));
        return redisSentinelConfiguration;
    }

    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setMaxRedirects(3);
        redisClusterConfiguration.addClusterNode(new RedisNode("192.168.13.111", 30010));
        redisClusterConfiguration.addClusterNode(new RedisNode("192.168.13.112", 30010));
        return redisClusterConfiguration;
    }

    private RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getIp());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());
        return redisStandaloneConfiguration;
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(LettuceClientConfiguration lettuceClientConfiguration) {
        if (redisProperties.isClusterMode()) {
            return new LettuceConnectionFactory(redisClusterConfiguration(), lettuceClientConfiguration);
        }

        if (redisProperties.isSentinelMode()) {
            return new LettuceConnectionFactory(redisSentinelConfiguration(), lettuceClientConfiguration);
        }

        return new LettuceConnectionFactory(redisStandaloneConfiguration(), lettuceClientConfiguration);
    }

    /**
     * 默认情况下的模板只能支持RedisTemplate<String, String>，也就是只能存入字符串，因此支持序列化
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory,
        CacheConfig cacheConfig) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        if (Func.isNotEmpty(cacheConfig.getValueSerializer())) {
            redisTemplate.setValueSerializer(cacheConfig.getValueSerializer());
        } else {
            redisTemplate.setValueSerializer(cacheConfig.isUseJacksonSerializer() ?
                new GenericJackson2JsonRedisSerializer() :
                new JdkSerializationRedisSerializer());
        }

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        if (Func.isNotEmpty(cacheConfig.getHashValueSerializer())) {
            redisTemplate.setHashValueSerializer(cacheConfig.getHashValueSerializer());
        } else {
            redisTemplate.setHashValueSerializer(cacheConfig.isUseJacksonSerializer() ?
                new GenericJackson2JsonRedisSerializer() :
                new JdkSerializationRedisSerializer());
        }

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }
}
