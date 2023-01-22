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
@TableName("user_weixin")
public class UserWeixin implements Serializable {

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
    private Boolean isDeleted;

    /**
     * 用户id
     */
    private String pkUserId;

    /**
     * 微信unionId
     */
    private String vxUnionId;

    /**
     * 微信openId
     */
    private String vxOpenId;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String portrait;

    /**
     * 城市
     */
    private String city;

    /**
     * 性别;1男2女
     */
    private Integer gender;
}
