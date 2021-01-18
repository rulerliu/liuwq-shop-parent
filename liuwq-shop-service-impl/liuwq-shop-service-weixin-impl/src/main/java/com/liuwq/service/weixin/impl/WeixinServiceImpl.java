package com.liuwq.service.weixin.impl;

import com.liuwq.service.weixin.api.WeixinService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeixinServiceImpl implements WeixinService {

    @Override
    public String appInfo(Long userId) {
        return "userId:" + userId;
    }
}
