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
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 广告位key
     */
    private String spaceKey;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDel;

    private List<PromotionAdDTO> promotionAdDTOS;

}
