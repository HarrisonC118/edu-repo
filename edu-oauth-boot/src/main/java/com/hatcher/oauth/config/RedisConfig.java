package com.hatcher.oauth.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author hatcher
 * @date 2023/2/23 17:09
 * @description
 */
@SpringBootConfiguration
@EnableRedisHttpSession
public class RedisConfig {
}
