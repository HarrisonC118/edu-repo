package com.hatcher.remote;

import com.hatcher.dto.PromotionAdDTO;
import com.hatcher.dto.PromotionSpaceDTO;
import com.hatcher.response.ResponseDTO;

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

    /**
     * 保存或更新广告位信息
     *
     * @param spaceDTO 广告位信息
     * @return PromotionSpaceDTO
     */
    ResponseDTO saveOrUpdateSpace(PromotionSpaceDTO spaceDTO);

    /**
     * 根据id获取广告位信息
     *
     * @param id
     * @return
     */
    PromotionSpaceDTO getSpaceById(String id);

    /**
     * 根据id删除广告位信息
     *
     * @param id
     * @return
     */
    ResponseDTO deleteSpaceById(String id);

    /**
     * 获取所有广告信息
     *
     * @return List<PromotionAdDTO>
     */
    List<PromotionAdDTO> getAllAds();

    /**
     * 保存或更新广告信息
     *
     * @param adDTO
     * @return
     */
    ResponseDTO saveOrUpdateAd(PromotionAdDTO adDTO);
}
