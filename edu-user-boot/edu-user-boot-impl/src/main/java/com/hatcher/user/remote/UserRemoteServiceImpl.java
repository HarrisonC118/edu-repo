package com.hatcher.user.remote;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hatcher.bo.UserQueryBO;
import com.hatcher.dto.UserDTO;
import com.hatcher.remote.UserRemoteService;
import com.hatcher.user.entity.User;
import com.hatcher.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @author hatcher
 * @date 2023/1/22 21:29
 * @description
 */
@DubboService
@Slf4j
public class UserRemoteServiceImpl implements UserRemoteService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private UserService userService;

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userService.getById(userId);
        if (null == user) {
            return null;
        }
        UserDTO dto = new UserDTO();
        BeanUtil.copyProperties(user, dto);
        return dto;
    }

    @Override
    public UserDTO getUserByPhone(String phone) {
        List<User> list = userService.lambdaQuery().eq(User::getPhone, phone).orderByDesc(User::getId).list();
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }
        UserDTO dto = new UserDTO();
        BeanUtil.copyProperties(list.get(0), dto);
        return dto;
    }

    @Override
    public Boolean isRegister(String phone) {
        UserDTO userByPhone = getUserByPhone(phone);
        return null != userByPhone;
    }

    @Override
    public Page<UserDTO> getUserListByPageN(Integer currentPage, Integer pageSize) {
        // TODO: 2021/1/22 不理解需求
        Page<UserDTO> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        return page;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        BeanUtil.copyProperties(userDTO, user);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        // 重新设置用户昵称
        if (StrUtil.isNotBlank(user.getPhone())) {
            String phone = userDTO.getPhone();
            user.setName("用户" + phone.substring(phone.length() - 4));
        }
        userService.save(user);
        BeanUtil.copyProperties(user, userDTO);
        log.info("用户[{}]保存成功", user);
        return userDTO;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return false;
    }

    @Override
    public boolean isUpdatedPassword(Integer userId) {
        return false;
    }

    @Override
    public boolean setPassword(Integer userId, String password, String configPassword) {
        return false;
    }

    @Override
    public boolean updatePassword(Integer userId, String oldPassword, String newPassword, String configPassword) {
        return false;
    }

    @Override
    public Page<UserDTO> getUserPages(UserQueryBO userQueryBO) {
        return null;
    }

    @Override
    public boolean forbidUser(Integer userId) {
        return false;
    }
}
