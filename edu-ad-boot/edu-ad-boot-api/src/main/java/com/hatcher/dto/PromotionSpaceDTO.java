package com.hatcher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hatcher
 * @date 2022/12/31 10:07
 * @description PromotionSpace远程调用的DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PromotionSpaceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private Integer revision;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Boolean isDeleted;

    private String name;

    private String spaceKey;
    private List<PromotionAdDTO> promotionAdDTOS;

}
