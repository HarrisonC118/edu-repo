package com.hatcher.oauth.authenticate;

import cn.hutool.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

/**
 * @author hatcher
 * @date 2023/2/24 19:17
 * @description
 */
@Component
public class JwtLogoutHandler implements LogoutHandler {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        String phone = JWTUtil.parseToken(jwt).getPayload("phone").toString();
        redisTemplate.delete(phone);
        SecurityContextHolder.clearContext();
    }
}
