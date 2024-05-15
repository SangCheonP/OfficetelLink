package com.ssafy.offistellink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    // Redis 서버와의 연결을 생성하는 팩토리
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> template = new RedisTemplate<>(); // Redis 서버와 상호 작용하는 데 사용되는 주요 템플릿 클래스 객체 생성
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer()); // 저장될 키의 직렬화 방식으로 문자열 직렬화를 사용
        template.setValueSerializer(new GenericToStringSerializer<>(String.class)); // 저장될 값의 직렬화 방식을 설정
        return template;
    }
}
