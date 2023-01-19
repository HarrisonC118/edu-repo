package com.hatcher.ad.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "PromotionAd对象", description = "")
public class PromotionAd implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("主键")
  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private String id;

  @ApiModelProperty("乐观锁")
  @TableField(fill = FieldFill.INSERT)
  @Version
  private Integer revision;

  @ApiModelProperty("创建时间")
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime gmtCreate;

  @ApiModelProperty("更新时间")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime gmtModified;

  @ApiModelProperty("逻辑删除;1 表示删除，0 表示未删除。")
  @TableField(fill = FieldFill.INSERT)
  private Boolean isDeleted;

  @ApiModelProperty("广告名称")
  private String name;

  @ApiModelProperty("广告位id")
  private String pkSpaceId;

  @ApiModelProperty("精确搜索的关键字")
  private String keyword;

  @ApiModelProperty("静态广告的内容")
  private String htmlContent;

  @ApiModelProperty("文字描述")
  private String textContent;

  @ApiModelProperty("链接地址")
  private String link;

  @ApiModelProperty("广告开始时间")
  private LocalDateTime startTime;

  @ApiModelProperty("广告结束时间")
  private LocalDateTime endTime;

  @ApiModelProperty("是否被禁用;1 表示禁用，0 表示未禁用。")
  private Boolean status;

  @ApiModelProperty("优先级")
  private Integer priority;

  @ApiModelProperty("图片地址")
  private String imgUrl;
}
