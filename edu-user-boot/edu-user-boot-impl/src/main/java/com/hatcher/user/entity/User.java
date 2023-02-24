package com.hatcher.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author hatcher
 * @since 2023-01-22
 */
@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 乐观锁
     */
    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer revision;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    /**
     * 逻辑删除;1 表示删除，0 表示未删除。
     */
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDeleted;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户头像地址
     */
    private String portrait;

    /**
     * 注册手机
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册ip
     */
    private String regIp;

    /**
     * 是否有效
     */
    @TableField(fill = FieldFill.INSERT)
    private Boolean isValid;

    /**
     * 是否过期
     */
    @TableField(fill = FieldFill.INSERT)
    private Boolean isExpired;

    /**
     * 是否被锁定
     */
    @TableField(fill = FieldFill.INSERT)
    private Boolean isLocked;

    /**
     * 用户状态;ENABLE：能登录 DISABLE：不能登录
     */
    @TableField(fill = FieldFill.INSERT)
    private String status;

}
