package com.yeeph.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
class AuthSessionConfig {

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

//    @Bean
//    public CookieSerializer cookieSerializer() {
//        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//        serializer.setCookieName("YEEPH_TOKEN");
//        serializer.setDomainName("yeeph.com");
//        return serializer;
//    }
}
