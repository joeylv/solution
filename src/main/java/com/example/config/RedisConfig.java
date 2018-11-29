package com.example.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@EnableAutoConfiguration
public class RedisConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.redis")
    public RedisStandaloneConfiguration mainRedisConfig() {

        return new RedisStandaloneConfiguration();
    }

    @Bean
    @Primary
    public LettuceConnectionFactory mainRedisConnectionFactory(RedisStandaloneConfiguration mainRedisConfig) {
        return new LettuceConnectionFactory(mainRedisConfig);
    }

    @Bean
    public RedisTemplate<String, String> mainRedisTemplate(LettuceConnectionFactory mainRedisConnectionFactory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(mainRedisConnectionFactory);
        return redisTemplate;
    }


    @Bean
    public RedisTemplate<String, String> bakRedisTemplate(LettuceConnectionFactory mainRedisConnectionFactory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(mainRedisConnectionFactory);
        return redisTemplate;
    }


}
