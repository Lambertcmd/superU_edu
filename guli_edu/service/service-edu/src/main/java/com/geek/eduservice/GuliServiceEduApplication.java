package com.geek.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class GuliServiceEduApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuliServiceEduApplication.class,args);
    }
}
