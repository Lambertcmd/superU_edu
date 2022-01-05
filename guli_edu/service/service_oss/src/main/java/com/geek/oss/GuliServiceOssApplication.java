package com.geek.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName GuliServiceOssApplication
 * @Description TODO
 * @Author Lambert
 * @Date 2022/1/5 14:12
 * @Version 1.0
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.geek"})
public class GuliServiceOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuliServiceOssApplication.class,args );
    }
}
