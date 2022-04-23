package com.geek.edustatistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName ServiceEduStatisticsApplication
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/22 18:43
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan({"com.geek"})
@EnableScheduling
@MapperScan("com.geek.edustatistics.mapper")
public class ServiceEduStatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduStatisticsApplication.class, args);
    }
}
