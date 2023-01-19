package com.hatcher.ad.remote;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hatcher.ad.entity.PromotionAd;
import com.hatcher.ad.entity.PromotionSpace;
import com.hatcher.ad.service.PromotionAdService;
import com.hatcher.ad.service.PromotionSpaceService;
import com.hatcher.dto.PromotionAdDTO;
import com.hatcher.dto.PromotionSpaceDTO;
import com.hatcher.remote.AdRemoteService;
import com.hatcher.util.ConvertUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hatcher
 * @date 2022/12/31 10:16
 * @description
 */
@DubboService
public class AdRemoteServiceImpl implements AdRemoteService {
    @Autowired
    private PromotionSpaceService promotionSpaceService;
    @Autowired
    private PromotionAdService promotionAdService;

    @Override
    public List<PromotionSpaceDTO> getAllSpaces() {
        List<PromotionSpace> list = promotionSpaceService.list();
        return ConvertUtil.copyListProperties(list, PromotionSpaceDTO::new);
    }

    @Override
    public List<PromotionSpaceDTO> getAdsBySpaceId(String[] spaceKeys) {
        List<PromotionSpaceDTO> spaceDTOList = new ArrayList<>();
        for (String spaceKey : spaceKeys) {
            QueryWrapper<PromotionSpace> spaceWrapper = new QueryWrapper();
            spaceWrapper.eq("space_key", spaceKey);
            // 获取广告位信息
            PromotionSpace promotionSpace = promotionSpaceService.getOne(spaceWrapper);

            // 获取当前广告位下的所有广告信息 状态为上架 在有效期内
            QueryWrapper<PromotionAd> adWrapper = new QueryWrapper();
            adWrapper.eq("pk_space_id", promotionSpace.getId());
            adWrapper.eq("status", 1);
            LocalDateTime now = LocalDateTime.now();
            adWrapper.ge("end_time", now);
            adWrapper.le("start_time", now);
            List<PromotionAd> adList = promotionAdService.list(adWrapper);


            // 把adList变成adDTOList
            List<PromotionAdDTO> promotionSpaceDTOS = ConvertUtil.copyListProperties(adList, PromotionAdDTO::new);


            // 把Space转换成DTO
            PromotionSpaceDTO promotionSpaceDTO = new PromotionSpaceDTO();
            BeanUtils.copyProperties(promotionSpace, promotionSpaceDTO);
            promotionSpaceDTO.setPromotionAdDTOS(promotionSpaceDTOS);
            spaceDTOList.add(promotionSpaceDTO);
        }
        return spaceDTOList;
    }
}

