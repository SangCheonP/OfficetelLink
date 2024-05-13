package com.ssafy.offistellink.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.ssafy.offistellink.model.mapper")
@Configuration
public class MybatisConfig {
}
