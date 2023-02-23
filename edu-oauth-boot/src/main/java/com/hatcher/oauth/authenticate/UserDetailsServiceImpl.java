package com.hatcher.oauth.authenticate;

import com.hatcher.remote.UserRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author hatcher
 * @date 2023/2/23 17:08
 * @description
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @DubboReference
    private UserRemoteService userRemoteServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        // 查询用户信息
        if (!userRemoteServiceImpl.isRegister(phone)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUser(userRemoteServiceImpl.getUserByPhone(phone));
        log.warn("用户信息为：" + userDetails.getUser());
        return userDetails;
    }
}
