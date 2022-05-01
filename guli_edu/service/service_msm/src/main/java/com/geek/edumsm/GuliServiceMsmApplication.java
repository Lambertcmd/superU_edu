package com.geek.edumsm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName GuliServiceMsmApplication
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/28 20:46
 * @Version 1.0
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源自动配置
@ComponentScan({"com.geek"})
@EnableDiscoveryClient
//@MapperScan("com.geek.edumsm.mapper")
public class GuliServiceMsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuliServiceMsmApplication.class, args);
    }
}
