package com.liuwq.service.member.impl;

import com.liuwq.service.member.api.MemberService;
import com.liuwq.service.member.feign.WeixinServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl implements MemberService {

    @Autowired
    private WeixinServiceFeign weixinServiceFeign;

    public String memberToWeixin(Long appId) {
        return "会员调用微信服务：" + weixinServiceFeign.appInfo(appId);
    }

}
