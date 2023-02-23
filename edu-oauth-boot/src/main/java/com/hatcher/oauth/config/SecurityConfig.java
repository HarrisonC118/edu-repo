package com.hatcher.oauth.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author hatcher
 * @date 2023/2/23 16:58
 * @description 用于配置Spring Security的配置类
 */
@SpringBootConfiguration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .logout()
                .and()
                .formLogin()
                .and()
                .oauth2Login();
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
