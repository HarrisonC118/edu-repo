package com.hatcher.remote;

import com.hatcher.dto.PromotionSpaceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author hatcher
 * @date 2022/12/31 10:07
 * @description
 */
@FeignClient(name = "edu-ad", path = "/ad")
public interface AdRemoteService {
    /**
     * 获取所有的广告位信息
     *
     * @return 广告位信息
     */
    @GetMapping("/space/getAllSpaces")
    List<PromotionSpaceDTO> getAllSpaces();
}
