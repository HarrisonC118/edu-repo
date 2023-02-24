package com.hatcher.oauth.entity;

import lombok.Data;

/**
 * @author hatcher
 * @date 2023/2/24 16:39
 * @description
 */
@Data
public class UserLoginBO {
    private String phone;
    private String password;
}
