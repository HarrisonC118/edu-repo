package com.hatcher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author hatcher
 * @date 2023/1/5 22:06
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PromotionAdDTO {
    private static final long serialVersionUID = 1L;


    private Integer id;

    /**
     * 广告名
     */
    private String name;

    /**
     * 广告位id
     */

    private Integer spaceId;

    /**
     * 精确搜索关键词
     */
    private String keyword;

    /**
     * 静态广告的内容
     */

    private String htmlContent;

    /**
     * 文字
     */
    private String text;

    /**
     * 链接
     */
    private String link;

    /**
     * 开始时间
     */

    private LocalDateTime startTime;

    /**
     * 结束时间
     */

    private LocalDateTime endTime;


    private LocalDateTime createTime;


    private LocalDateTime updateTime;

    private Integer status;

    /**
     * 优先级
     */
    private Integer priority;

    private String img;

}
