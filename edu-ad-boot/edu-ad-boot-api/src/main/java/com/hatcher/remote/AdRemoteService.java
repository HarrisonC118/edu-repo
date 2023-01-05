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
}
