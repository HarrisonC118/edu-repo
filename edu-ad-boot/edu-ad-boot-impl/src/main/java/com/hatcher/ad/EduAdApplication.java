package com.hatcher.ad;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hatcher
 * @date 2022/12/31 9:48
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.hatcher.ad.mapper")
@EnableDubbo
public class EduAdApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduAdApplication.class, args);
    }
}
