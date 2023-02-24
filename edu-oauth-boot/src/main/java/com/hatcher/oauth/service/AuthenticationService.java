package com.hatcher.oauth.service;

import com.hatcher.dto.UserDTO;
import com.hatcher.oauth.entity.UserLoginBO;
import com.hatcher.response.ResponseDTO;

/**
 * @author hatcher
 * @date 2023/2/24 18:21
 * @description
 */
public interface AuthenticationService {
    /**
     * 登录
     *
     * @param user
     * @return
     */
    ResponseDTO login(UserLoginBO user);

    /**
     * 注册
     *
     * @param userDTO
     * @return
     */
    ResponseDTO register(UserDTO userDTO);

}
