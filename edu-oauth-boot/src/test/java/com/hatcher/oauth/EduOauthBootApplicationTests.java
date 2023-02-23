package com.hatcher.oauth;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
class EduOauthBootApplicationTests {

	@Test
	void contextLoads() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodeResult = encoder.encode("123456");
		log.warn("加密结果为：" + encodeResult);
	}

}
