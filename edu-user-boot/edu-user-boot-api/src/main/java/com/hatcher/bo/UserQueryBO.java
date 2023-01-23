package com.hatcher.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author hatcher
 * @date 2023/1/23 20:52
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryBO {
    private Integer currentPage;
    private Integer pageSize;
    private String phone;
    private Integer userId;
    private LocalDateTime startCreateTime;
    private LocalDateTime endCreateTime;
}
