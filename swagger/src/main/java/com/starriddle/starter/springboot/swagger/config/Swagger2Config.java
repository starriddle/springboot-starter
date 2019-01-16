package com.starriddle.starter.springboot.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 全局配置
 *
 * @author CYL
 * @date 2019-01-16
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.starriddle.starter.springboot.swagger.controller"))
                .paths(PathSelectors.regex("/swagger2/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Swagger2 Demo Project Restful API")
                .description("Spring Boot Swagger2 演示项目 的 Restful API")
                .termsOfServiceUrl("http://127.0.0.1:11002/")
                .contact(new Contact("ChenYL", null, "chen.86@outlook.com"))
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}
