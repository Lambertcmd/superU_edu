package com.geek.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServiceEduOrderApplication
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/20 23:11
 * @Version 1.0
 **/
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"com.geek"})
@MapperScan("com.geek.eduorder.mapper")
public class ServiceEduOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduOrderApplication.class, args);
    }
}
