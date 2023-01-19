package com.hatcher.ad.controller;

import com.hatcher.dto.PromotionSpaceDTO;
import com.hatcher.remote.AdRemoteService;
import com.hatcher.response.ResponseDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hatcher
 * @date 2023/1/19 20:44
 * @description
 */
@RequestMapping("/ad")
@RestController
public class AdController {
    @DubboReference
    private AdRemoteService adRemoteService;

    @RequestMapping("getAdBySpaceKey")
    public ResponseDTO getAdBySpaceKey(String[] spaceKeys) {
        List<PromotionSpaceDTO> adsBySpaceId = adRemoteService.getAdsBySpaceId(spaceKeys);
        return ResponseDTO.success(adsBySpaceId);
    }
}
