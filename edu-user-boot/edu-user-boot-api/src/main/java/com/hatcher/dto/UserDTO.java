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
public class UserDTO implements Serializable {
    private String id;
    private Integer revision;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Boolean isDeleted;
    private String name;
    private String portrait;
    private String phone;
    private String password;
    private String regIp;
    private Boolean isValid;
    private Boolean isExpired;
    private Boolean isLocked;
    private String status;
    private String salt;
}
