package com.geek.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName GuliServiceOssApplication
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/5 14:12
 * @Version 1.0
 **/
//默认会加载数据库 视频点播服务不需要数据库 加上该注解不会加载数据库
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.geek"})
@EnableDiscoveryClient
public class GuliServiceOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuliServiceOssApplication.class,args );
    }
}
