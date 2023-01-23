package com.hatcher.remote;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hatcher.bo.UserQueryBO;
import com.hatcher.dto.UserDTO;

/**
 * @author hatcher
 * @date 2023/1/22 21:21
 * @description
 */
public interface UserRemoteService {
    /**
     * 根据用户id获取用户信息
     *
     * @param userId
     * @return User
     */
    UserDTO getUserById(Long userId);

    /**
     * 根据手机号获取用户信息
     *
     * @param phone
     * @return User
     */
    UserDTO getUserByPhone(String phone);

    /**
     * 判断用户是否已经注册
     *
     * @param phone
     * @return
     */
    Boolean isRegister(String phone);

    /**
     * 分页获取用户列表
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<UserDTO> getUserListByPageN(Integer currentPage, Integer pageSize);

    /**
     * 保存用户信息
     *
     * @param userDTO
     * @return
     */
    UserDTO saveUser(UserDTO userDTO);

    /**
     * 更新用户信息
     *
     * @param userDTO
     * @return
     */
    boolean updateUser(UserDTO userDTO);

    /**
     * 是否已经修改过密码
     *
     * @param userId
     * @return
     */
    boolean isUpdatedPassword(Integer userId);

    /**
     * 设置密码
     *
     * @param userId
     * @param password
     * @param configPassword
     * @return
     */
    boolean setPassword(Integer userId, String password, String configPassword);

    /**
     * 修改密码
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @param configPassword
     * @return
     */
    boolean updatePassword(Integer userId, String oldPassword, String newPassword, String configPassword);

    /**
     * 根据条件查询用户列表
     *
     * @param userQueryBO
     * @return
     */
    Page<UserDTO> getUserPages(UserQueryBO userQueryBO);

    /**
     * 禁用用户
     *
     * @param userId
     * @return
     */
    boolean forbidUser(Integer userId);
}
