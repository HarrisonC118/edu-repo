package com.hatcher.ad.controller;

import com.hatcher.remote.AdRemoteService;
import com.hatcher.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AdRemoteService adRemoteService;

    @RequestMapping("space/getAllSpaces")
    public ResponseDTO getAllSpaces() {
        return ResponseDTO.success(adRemoteService.getAllSpaces());
    }
}
