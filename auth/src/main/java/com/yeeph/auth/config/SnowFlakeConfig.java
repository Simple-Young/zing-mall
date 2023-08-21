package com.yeeph.auth.config;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
public class SnowFlakeConfig {

    @Bean
    public SnowflakeGenerator snowflakeGenerator(Environment environment){
        return new SnowflakeGenerator(
                Long.parseLong(Objects.requireNonNull(environment.getProperty("snowflake.workerId"))),
                Long.parseLong(Objects.requireNonNull(environment.getProperty("snowflake.dataCenterId"))));
    }
}
