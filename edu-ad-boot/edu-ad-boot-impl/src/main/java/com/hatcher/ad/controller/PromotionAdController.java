package com.hatcher.ad.controller;


import com.hatcher.ad.entity.PromotionAd;
import com.hatcher.ad.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hatcher
 * @since 2022-12-31
 */
@RestController
@RequestMapping("/ad/promotion-ad")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;

    @GetMapping("/testAutoFill")
    public String testAutoFill() {
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setName("test");
        promotionAd.setHtmlContent("test");
        promotionAd.setLink("test");
        promotionAd.setStartTime(LocalDateTime.now());
        promotionAd.setEndTime(LocalDateTime.of(2023, 12, 31, 0, 0, 0));
        promotionAd.setImgUrl("test");
        promotionAdService.save(promotionAd);
        return "success";
    }
}
