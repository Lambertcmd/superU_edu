package com.geek.servicebase.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;


/**
 * @ClassName Swaager3Config
 * @Description TODO
 * @Author Lambert
 * @Date 2021/12/27 23:04
 * @Version 1.0
 **/
@Configuration
@EnableOpenApi
public class Swagger3Config {
    private static final List<String> DEFAULT_EXCLUDE_PATH = Arrays.asList("/error","/actuator/**","/*/error");


    Boolean swaggerEnabled=true;//ture 启用Swagger3.0 fasle 禁用（生产环境要禁用）
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled)
                .select()
                // 扫描的路径使用@Api的controller
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 指定路径处理PathSelectors.any()代表所有的路径
//                .paths(PathSelectors.any())
                .paths(PathSelectors.ant("/admin/*").negate())//接口路径为/admin开头的任何接口不写入Swagger
                .paths(PathSelectors.ant("/error.*").negate())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("适用于前后端分离统一的接口文档")
                //作者信息
                //.contact(new Contact("name","url", "email"))
                .contact(new Contact("Lambert", "null", "2473758409@qq.com"))
                .version("1.0")
                .build();
    }
}