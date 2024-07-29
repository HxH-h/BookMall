package com.bookmall.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Knife4jConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        log.info("初始化Knife4j");
        return new OpenAPI()
                // 接口文档标题
                .info(new Info().title("API接口文档")
                        // 接口文档简介
                        .description("图书商城接口文档")
                        // 接口文档版本
                        .version("1.0")
                        );
//                .externalDocs(new ExternalDocumentation()
//                        .description("图书商城接口文档")
//                        .url("http://127.0.0.1:8080"));
    }

}
