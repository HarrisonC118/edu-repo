package com.hatcher.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String id;

    private Integer revision;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Boolean isDeleted;

    private String name;

    private String pkSpaceId;

    private String keyword;

    private String htmlContent;

    private String textContent;

    private String link;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private Boolean status;

    private Integer priority;

    private String imgUrl;

}
