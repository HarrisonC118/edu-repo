package com.hatcher.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hatcher.user.entity.UserPhoneVerificationCode;
import com.hatcher.user.mapper.UserPhoneVerificationCodeMapper;
import com.hatcher.user.service.UserPhoneVerificationCodeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hatcher
 * @since 2023-01-22
 */
@Service
public class UserPhoneVerificationCodeServiceImpl extends ServiceImpl<UserPhoneVerificationCodeMapper, UserPhoneVerificationCode> implements UserPhoneVerificationCodeService {

}
