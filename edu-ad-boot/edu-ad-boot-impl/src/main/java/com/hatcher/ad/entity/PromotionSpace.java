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
 * @since 2023-01-19
 */
@Getter
@Setter
@TableName("promotion_space")
@ApiModel(value = "PromotionSpace对象", description = "")
public class PromotionSpace implements Serializable {

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
  private Boolean isDeleted;

  @ApiModelProperty("名称")
  private String name;

  @ApiModelProperty("广告位关键字")
  private String spaceKey;
}
