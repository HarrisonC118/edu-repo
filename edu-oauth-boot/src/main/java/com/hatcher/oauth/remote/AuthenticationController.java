package com.hatcher.oauth.remote;

import com.hatcher.dto.UserDTO;
import com.hatcher.oauth.entity.UserLoginBO;
import com.hatcher.oauth.service.AuthenticationService;
import com.hatcher.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hatcher
 * @date 2023/2/24 16:34
 * @description
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody UserLoginBO user) {
        ResponseDTO responseDTO = authenticationService.login(user);
        return responseDTO;
    }

    @PostMapping("/register")
    public ResponseDTO register(@RequestBody UserDTO userDTO) {
        ResponseDTO register = authenticationService.register(userDTO);
        return register;
    }
}
