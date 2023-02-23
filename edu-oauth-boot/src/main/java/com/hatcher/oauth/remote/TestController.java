package com.hatcher.oauth.remote;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hatcher
 * @date 2023/2/23 17:16
 * @description
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal().toString();
    }
}
