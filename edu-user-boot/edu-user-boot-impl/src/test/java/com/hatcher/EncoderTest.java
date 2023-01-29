package com.hatcher;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author hatcher
 * @date 2023/1/25 10:32
 * @description
 */
@Slf4j
public class EncoderTest {
    @Test
    public void testEncode() {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        log.info("encode: {}", encode);
        boolean result = encoder.matches("123456", encode);
        log.info("result: {}", result);
    }
}
