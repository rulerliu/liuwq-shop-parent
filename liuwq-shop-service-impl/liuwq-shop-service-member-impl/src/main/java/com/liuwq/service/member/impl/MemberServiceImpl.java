package com.liuwq.service.member.impl;

import com.liuwq.service.member.feign.WeixinServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl {

    @Autowired
    private WeixinServiceFeign weixinServiceFeign;

    @GetMapping("/appInfo")
    public String appInfo(Long userId) {
        return "会员调用微信服务：" + weixinServiceFeign.appInfo(userId);
    }

}
