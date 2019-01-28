package com.freedom.core.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * Swagger2 配置文件
 * @author Bai
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfigurer {

    @Autowired
    private MyYml yml;
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(yml.getSwaggerEnable())
                .select()
                .apis(RequestHandlerSelectors.basePackage(yml.getSwaggerPath()))//针对项目
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(yml.getSwaggerTitle())
                .description(yml.getSwaggerDescription())
                .termsOfServiceUrl(yml.getSwaggerServiceUrl())
                .contact(new Contact(yml.getSwaggerUserName(), yml.getSwaggerUserUrl(), yml.getSwaggerUserUrl()))
                .version(yml.getSwaggerVersion())
                .build();
    }

}

