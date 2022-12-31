package com.hatcher.ad.remote;

import com.hatcher.ad.entity.PromotionSpace;
import com.hatcher.ad.service.IPromotionSpaceService;
import com.hatcher.dto.PromotionSpaceDTO;
import com.hatcher.remote.AdRemoteService;
import com.hatcher.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hatcher
 * @date 2022/12/31 10:16
 * @description
 */
@RestController
@RequestMapping("/ad")
public class AdRemoteServiceImpl implements AdRemoteService {
    @Autowired
    private IPromotionSpaceService promotionSpaceService;

    @Override
    @GetMapping("/space/getAllSpaces")
    public List<PromotionSpaceDTO> getAllSpaces() {
        List<PromotionSpace> list = promotionSpaceService.list();
        return ConvertUtil.convertList(list, PromotionSpaceDTO.class);
    }
}

