package com.hatcher.oauth.service.impl;

import cn.hutool.jwt.JWTUtil;
import com.hatcher.dto.UserDTO;
import com.hatcher.oauth.entity.UserLoginBO;
import com.hatcher.oauth.service.AuthenticationService;
import com.hatcher.remote.UserRemoteService;
import com.hatcher.response.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author hatcher
 * @date 2023/2/24 18:24
 * @description
 */
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @DubboReference
    private UserRemoteService userRemoteServiceImpl;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseDTO login(UserLoginBO user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getPhone(), user.getPassword()));
        } catch (Exception e) {
            log.warn(e.getMessage());
        }

        Map map = new HashMap();
        map.put("phone", user.getPhone());
        String jwt = JWTUtil.createToken(map, "harrisonToken110".getBytes());
        String token = "Bearer " + jwt;
        // 手机号作为key，token作为value，存入redis中，设置过期时间为1天
        redisTemplate.boundValueOps(user.getPhone()).set(jwt, 10, TimeUnit.DAYS);
        return ResponseDTO.success(token);
    }

    @Override
    public ResponseDTO register(UserDTO userDTO) {
        if (userRemoteServiceImpl.isRegister(userDTO.getPhone())) {
            return ResponseDTO.ofError("该手机号已注册");
        }
        String realPassword = userDTO.getPassword();
        String encodePassword = new BCryptPasswordEncoder().encode(realPassword);
        userDTO.setPassword(encodePassword);
        userRemoteServiceImpl.saveUser(userDTO);
        return ResponseDTO.success("注册成功");
    }


}
