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
public class UserPhoneVerificationCode implements Serializable {
    private String id;
    private Integer revision;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Boolean isDeleted;
    private String phone;
    private String verificationCode;
    private Boolean isCheck;
    private Integer checkTimes;
}
