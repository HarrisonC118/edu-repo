package com.hatcher.user.remote;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        User queryUser = userService.lambdaQuery().eq(User::getId, userDTO.getId()).one();
        if (null == queryUser) {
            return false;
        }
        queryUser.setName(userDTO.getName());
        queryUser.setPassword(encoder.encode(userDTO.getPassword()));
        queryUser.setPhone(userDTO.getPhone());
        queryUser.setIsExpired(userDTO.getIsExpired());
        queryUser.setIsLocked(userDTO.getIsLocked());
        queryUser.setIsValid(userDTO.getIsValid());
        queryUser.setPortrait(userDTO.getPortrait());
        queryUser.setStatus(userDTO.getStatus());
        return userService.updateById(queryUser);
    }

    @Override
    public boolean isUpdatedPassword(Integer userId) {
        User one = userService.lambdaQuery().eq(User::getId, userId).one();
        if (null == one) {
            return false;
        }
        boolean matches = encoder.matches(one.getPhone(), one.getPassword());
        log.info("用户[{}]是否有修改过初始密码[{}]", userId, matches);
        return true;
    }

    @Override
    public boolean setPassword(Integer userId, String password, String configPassword) {
        User user = userService.getById(userId);
        if (null == user) {
            return false;
        }
        if (!StrUtil.equals(password, configPassword)) {
            return false;
        }
        user.setPassword(encoder.encode(password));
        this.userService.updateById(user);
        log.info("用户[{}]设置密码成功", userId);
        return true;
    }

    @Override
    public boolean updatePassword(Integer userId, String oldPassword, String newPassword, String configPassword) {
        User user = userService.getById(userId);
        if (null == user) {
            return false;
        }
        if (!StrUtil.equals(newPassword, configPassword)) {
            return false;
        }
        if (!encoder.matches(oldPassword, user.getPassword())) {
            log.info("用户[{}]旧密码错误", userId);
            return false;
        }
        user.setPassword(encoder.encode(newPassword));
        userService.updateById(user);
        log.info("用户[{}]更新密码成功", userId);
        return true;
    }

    @Override
    public Page<UserDTO> getUserPages(UserQueryBO userQueryBO) {
        String phone = userQueryBO.getPhone();
        Integer userId = userQueryBO.getUserId();
        Integer currentPage = userQueryBO.getCurrentPage();
        Integer pageSize = userQueryBO.getPageSize();
        LocalDateTime startCreateTime = userQueryBO.getStartCreateTime();
        LocalDateTime endCreateTime = userQueryBO.getEndCreateTime();
        Page<User> page = new Page<>(currentPage, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //根据课程名称查询
        if (StrUtil.isNotBlank(phone)) {
            queryWrapper.like("phone", phone);
        }
        if (null != startCreateTime && null != endCreateTime) {
            queryWrapper.ge("gmt_create", startCreateTime);
            queryWrapper.le("gmt_create", endCreateTime);
        }
        if (null != userId && userId > 0) {
            queryWrapper.eq("id", userId);
        }
        //根据课程状态查询
        long count = userService.count(queryWrapper);
        queryWrapper.orderByDesc("id");
        IPage<User> selectPage = userService.getBaseMapper().selectPage(page, queryWrapper);

        List<UserDTO> userDTOList = new ArrayList<>();
        //获取课程对应的模块的信息
        for (User user : selectPage.getRecords()) {
            UserDTO userDTO = new UserDTO();
            BeanUtil.copyProperties(user, userDTO);
            userDTOList.add(userDTO);
        }
        Page<UserDTO> result = new Page<>();
        //分页查询结果对象属性的拷贝
        BeanUtil.copyProperties(selectPage, result);
        //设置分页结果对象record属性
        result.setRecords(userDTOList);
        result.setTotal(count);
        return result;
    }

    @Override
    public boolean forbidUser(Integer userId) {
        User user = userService.getById(userId);
        if (null == user) {
            return false;
        }
        user.setIsDeleted(true);
        user.setStatus("DISABLE");
        boolean result = userService.updateById(user);
        if (result) {
            // TODO 发送mq消息，让用户登录失效
        }
        return result;
    }

    @Override
    public boolean isExistUser(String username) {
        User user = userService.lambdaQuery().eq(User::getPhone, username).one();
        if (user != null) {
            return true;
        }
        return false;
    }
}
