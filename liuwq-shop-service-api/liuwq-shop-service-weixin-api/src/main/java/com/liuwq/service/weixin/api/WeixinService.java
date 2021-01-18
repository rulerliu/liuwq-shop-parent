package com.liuwq.service.weixin.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface WeixinService {

    @GetMapping("appInfo")
    String appInfo(@RequestParam("userId") Long userId);

}
