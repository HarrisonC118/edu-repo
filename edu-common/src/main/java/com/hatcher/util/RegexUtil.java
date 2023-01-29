package com.hatcher.util;

/**
 * @author hatcher
 * @date 2023/1/29 15:02
 * @description 正则工具类
 */
public class RegexUtil {
    public static final String PHONE_REGEX = "^1[3|4|5|7|8][0-9]\\d{8}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    /**
     * 验证手机号
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        return phone.matches(PHONE_REGEX);
    }
}
