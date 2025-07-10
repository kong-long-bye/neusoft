package com.neusoft.medical.common.config;

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
 * Swagger配置类
 * @author Neusoft
 * @date 2025-07-10
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 创建API基本信息
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 指定扫描的controller包路径
                .apis(RequestHandlerSelectors.basePackage("com.neusoft.medical.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API文档基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("东软医疗保险报销系统API文档")
                .description("医疗保险报销系统后端API接口文档")
                .version("1.0.0")
                .contact(new Contact("Neusoft", "", "neusoft@medical.com"))
                .build();
    }
}