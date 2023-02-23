package com.hatcher.oauth;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 11090
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
public class EduOauthBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduOauthBootApplication.class, args);
	}

}
