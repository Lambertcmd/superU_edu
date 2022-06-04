package com.geek.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName GuliServiceEduApplication
 * @Description TODO
 * @Author Lambert
 * @Date 2021/12/27 16:49
 * @Version 1.0
 **/
@ComponentScan(basePackages = {"com.geek"})
@SpringBootApplication
@MapperScan("com.geek.eduservice.mapper")
@EnableDiscoveryClient //nacos注册
@EnableFeignClients    //Feign服务调用
public class GuliServiceEduApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuliServiceEduApplication.class,args);
    }
}
