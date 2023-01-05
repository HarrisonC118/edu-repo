package com.hatcher.ad.remote;

import com.hatcher.ad.entity.PromotionSpace;
import com.hatcher.ad.service.IPromotionSpaceService;
import com.hatcher.dto.PromotionSpaceDTO;
import com.hatcher.remote.AdRemoteService;
import com.hatcher.util.ConvertUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author hatcher
 * @date 2022/12/31 10:16
 * @description
 */
@DubboService
public class AdRemoteServiceImpl implements AdRemoteService {
    @Autowired
    private IPromotionSpaceService promotionSpaceService;

    @Override
    public List<PromotionSpaceDTO> getAllSpaces() {
        List<PromotionSpace> list = promotionSpaceService.list();
        return ConvertUtil.copyListProperties(list, PromotionSpaceDTO::new);
    }
}

