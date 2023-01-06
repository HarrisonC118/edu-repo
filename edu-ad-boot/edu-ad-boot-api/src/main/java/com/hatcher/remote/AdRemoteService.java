package com.hatcher.remote;

import com.hatcher.dto.PromotionSpaceDTO;

import java.util.List;

/**
 * @author hatcher
 * @date 2022/12/31 10:07
 * @description
 */

public interface AdRemoteService {
    /**
     * 获取所有广告位信息
     *
     * @return List<PromotionSpaceDTO>
     */
    List<PromotionSpaceDTO> getAllSpaces();

    /**
     * 根据广告位id获取广告信息
     *
     * @param spaceKeys 广告位id
     * @return List<PromotionSpaceDTO>
     */
    List<PromotionSpaceDTO> getAdsBySpaceId(String[] spaceKeys);
}
