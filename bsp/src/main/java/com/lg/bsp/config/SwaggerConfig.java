package com.lg.bsp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Description swagger配置类
 * @Author jincheng
 * @Date 2022/1/7 14:04
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.enable}")
    private boolean enable = true;

    /**
     * 添加摘要信息(Docket)
     */
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(new ApiInfoBuilder()
                        .title("标题：项目接口文档")
                        .description("项目")
                        .contact(new Contact("项目", null, null))
                        .version("版本号:1.1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lg.bsp.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
