package com.hatcher.ad.controller;


import com.hatcher.ad.entity.PromotionSpace;
import com.hatcher.ad.service.IPromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hatcher
 * @since 2022-12-31
 */
@RestController
@RequestMapping("/ad/space")
public class PromotionSpaceController {
    @Autowired
    private IPromotionSpaceService promotionSpaceService;

    @RequestMapping("getAllSpaces")
    public List<PromotionSpace> getAllSpaces() {
        List<PromotionSpace> list = promotionSpaceService.list();
        return list;
    }
}
