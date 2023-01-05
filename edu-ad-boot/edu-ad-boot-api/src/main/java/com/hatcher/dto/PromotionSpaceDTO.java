package com.hatcher.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hatcher
 * @date 2022/12/31 10:07
 * @description PromotionSpace远程调用的DTO
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpaceKey() {
        return spaceKey;
    }

    public void setSpaceKey(String spaceKey) {
        this.spaceKey = spaceKey;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "PromotionSpaceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", spaceKey='" + spaceKey + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDel=" + isDel +
                '}';
    }
}
