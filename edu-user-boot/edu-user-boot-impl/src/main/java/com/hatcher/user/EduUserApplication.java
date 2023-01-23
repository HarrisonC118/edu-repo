package com.hatcher.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hatcher
 * @date 2023/1/22 21:30
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.hatcher.user.mapper")
@EnableDubbo
public class EduUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduUserApplication.class, args);
    }
}
