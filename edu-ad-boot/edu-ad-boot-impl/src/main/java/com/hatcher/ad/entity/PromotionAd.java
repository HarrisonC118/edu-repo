package com.hatcher.ad.entity;

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
 * @since 2023å¹´01æ19æ¥
 */
@Getter
@Setter
@TableName("promotion_ad")
public class PromotionAd implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private String id;

  @TableField(fill = FieldFill.INSERT)
  @Version
  private Integer revision;

  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime gmtCreate;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime gmtModified;

  @TableField(fill = FieldFill.INSERT)
  private Boolean isDeleted;

  private String name;

  private String pkSpaceId;

  private String keyword;

  private String htmlContent;

  private String textContent;

  private String link;

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  private Boolean status;

  private Integer priority;

  private String imgUrl;
}
