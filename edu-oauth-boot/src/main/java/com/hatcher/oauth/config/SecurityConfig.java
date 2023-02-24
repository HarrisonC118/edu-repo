package com.hatcher.oauth.config;

import com.hatcher.oauth.authenticate.UserDetailsServiceImpl;
import com.hatcher.oauth.filter.JsonLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author hatcher
 * @date 2023/2/23 16:58
 * @description 用于配置Spring Security的配置类
 */
@SpringBootConfiguration
public class SecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public JsonLoginFilter jsonLoginFilter(AuthenticationManager authenticationManager) {
        JsonLoginFilter jsonLoginFilter = new JsonLoginFilter();
        jsonLoginFilter.setUsernameParameter("phone");
        jsonLoginFilter.setPasswordParameter("password");
        jsonLoginFilter.setFilterProcessesUrl("/doLogin");
//        jsonLoginFilter.setSessionAuthenticationStrategy(SessionAuthenticationStrategy);
        jsonLoginFilter.setAuthenticationManager(authenticationManager);
        jsonLoginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("登录成功");
        });
        jsonLoginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("登录失败" + exception);
        });
        return jsonLoginFilter;
    }

    @Bean
    public SecurityFilterChain securityChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http.authorizeHttpRequests()
//                .requestMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .and()
                .formLogin()
                .and()
                .csrf().disable();
        http.addFilterAt(jsonLoginFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    //    @Bean
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.userDetailsService(userDetailsService));
//    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 获取默认的AuthenticationManager
     *
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService);
//        authenticationManagerBuilder.authenticationProvider(customMobileAuthenticationProvider);
//        authenticationManagerBuilder.authenticationProvider(authProvider());
        return authenticationManagerBuilder.build();
    }
}
