package com.hatcher.ad.remote;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hatcher.ad.entity.PromotionAd;
import com.hatcher.ad.entity.PromotionSpace;
import com.hatcher.ad.service.PromotionAdService;
import com.hatcher.ad.service.PromotionSpaceService;
import com.hatcher.dto.PromotionAdDTO;
import com.hatcher.dto.PromotionSpaceDTO;
import com.hatcher.remote.AdRemoteService;
import com.hatcher.response.ResponseDTO;
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

    @Override
    public ResponseDTO saveOrUpdateSpace(PromotionSpaceDTO spaceDTO) {
        // 把DTO转换成Entity
        PromotionSpace promotionSpace = new PromotionSpace();
        BeanUtils.copyProperties(spaceDTO, promotionSpace);
        // 保存或更新
        try {
            //saveOrUpdate无法触发乐观锁，所以要手动判断
            if (promotionSpace.getId() == null) {
                promotionSpaceService.save(promotionSpace);
            } else {
                PromotionSpace byId = promotionSpaceService.getById(promotionSpace.getId());
                byId.setName(promotionSpace.getName());
                byId.setSpaceKey(promotionSpace.getSpaceKey());
                promotionSpaceService.updateById(byId);
            }
            return ResponseDTO.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.ofError("保存或更新失败");
        }
    }

    @Override
    public PromotionSpaceDTO getSpaceById(String id) {
        PromotionSpace promotionSpace = promotionSpaceService.getById(id);
        PromotionSpaceDTO promotionSpaceDTO = new PromotionSpaceDTO();
        BeanUtils.copyProperties(promotionSpace, promotionSpaceDTO);
        return promotionSpaceDTO;
    }

    @Override
    public ResponseDTO deleteSpaceById(String id) {
        try {
            promotionSpaceService.removeById(id);
            return ResponseDTO.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.ofError("删除失败");
        }
    }
}

