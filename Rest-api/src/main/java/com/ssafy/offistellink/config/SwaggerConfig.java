package com.ssafy.offistellink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration // 스프링 설정 클래스임을 나타냄
public class SwaggerConfig {

    @Bean // 스프링 빈으로 등록
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()) // API 구성 요소를 설정
                .info(apiInfo()); // API 정보를 설정
    }

    private Info apiInfo() {
        return new Info()
                .title("SSAFY 문서 API") // API 제목을 설정합니다.
                .description("<h3>RestAPI 문서 내용을 다음과 같이 제공합니다.</h3>") // API 설명을 설정합니다.
                .version("1.0.0"); // API 버전을 설정합니다.
    }
}

