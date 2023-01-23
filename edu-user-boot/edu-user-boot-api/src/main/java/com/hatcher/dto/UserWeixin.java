package com.hatcher.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class UserWeixin implements Serializable {
    private String id;
    private Integer revision;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Boolean isDeleted;
    private String pkUserId;
    private String vxUnionId;
    private String vxOpenId;
    private String nickName;
    private String portrait;
    private String city;
    private Integer gender;
}
