package com.hatcher.ad.controller;

import com.hatcher.remote.AdRemoteService;
import com.hatcher.response.ResponseDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("space/getAllSpaces")
    public ResponseDTO getAllSpaces() {
        return ResponseDTO.success(adRemoteService.getAllSpaces());
    }
}
