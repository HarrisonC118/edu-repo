package com.hatcher.ad.controller;

import com.hatcher.dto.PromotionAdDTO;
import com.hatcher.dto.PromotionSpaceDTO;
import com.hatcher.remote.AdRemoteService;
import com.hatcher.response.ResponseDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * @author hatcher
 * @date 2022/12/31 10:21
 * @description
 */
@RestController
@RequestMapping("/ad")
public class AdController {
    @DubboReference
    private AdRemoteService adRemoteService;

    @GetMapping("space/getAllSpaces")
    public ResponseDTO getAllSpaces() {
        return ResponseDTO.success(adRemoteService.getAllSpaces());
    }


    @PostMapping("space/saveOrUpdateSpace")
    public ResponseDTO saveOrUpdateSpace(@RequestBody PromotionSpaceDTO spaceDTO) {
        return adRemoteService.saveOrUpdateSpace(spaceDTO);
    }

    @GetMapping("space/getSpaceById")
    public ResponseDTO getSpaceById(@RequestParam("id") String id) {
        PromotionSpaceDTO spaceDTO = adRemoteService.getSpaceById(id);
        return ResponseDTO.success(spaceDTO);
    }

    @PostMapping("space/deleteSpaceById")
    public ResponseDTO deleteSpaceById(@RequestBody PromotionSpaceDTO spaceDTO) {
        return adRemoteService.deleteSpaceById(spaceDTO.getId());
    }

    @GetMapping("getAdsBySpaceKey")
    public ResponseDTO getAdsBySpaceKey(String[] spaceKeys) {
        return ResponseDTO.success(adRemoteService.getAdsBySpaceId(spaceKeys));
    }

    @GetMapping("getAllAds")
    public ResponseDTO getAllAds() {
        return ResponseDTO.success(adRemoteService.getAllAds());
    }

    @PostMapping("saveOrUpdateAd")
    public ResponseDTO saveOrUpdateAd(@RequestBody PromotionAdDTO adDTO) {
        return adRemoteService.saveOrUpdateAd(adDTO);
    }

    @GetMapping("getAdById")
    public ResponseDTO getAdById(@RequestParam("id") String id) {
        PromotionAdDTO adDTO = adRemoteService.getAdById(id);
        return ResponseDTO.success(adDTO);
    }
}
