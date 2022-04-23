package com.geek.canal;

import com.geek.canal.client.CanalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @ClassName CanalClientApplication
 * @Description TODO
 * @Author Lambert
 * @Date 2022/4/24 1:45
 * @Version 1.0
 **/
@SpringBootApplication
public class CanalClientApplication implements CommandLineRunner {
    @Resource
    private CanalClient canalClient;

    public static void main(String[] args) {
        SpringApplication.run(CanalClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //项目启动，执行canal客户端监听
        canalClient.run();
    }
}
