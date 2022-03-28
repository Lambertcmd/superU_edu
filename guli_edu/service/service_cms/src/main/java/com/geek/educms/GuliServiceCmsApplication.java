package com.geek.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName GuliServiceCmsApplication
 * @Description TODO
 * @Author Lambert
 * @Date 2022/3/23 17:56
 * @Version 1.0
 **/
@SpringBootApplication
@ComponentScan({"com.geek"}) //指定扫描位置
@MapperScan("com.geek.educms.mapper")
public class GuliServiceCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuliServiceCmsApplication.class, args);
    }
}
