package com.liuwq.service.member.impl;

import com.liuwq.service.member.api.MemberService;
import com.liuwq.service.member.feign.WeixinServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@RefreshScope // 加上这个注解实时刷新配置文件
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private WeixinServiceFeign weixinServiceFeign;
    @Value("${project.env}")
    private String env;

    public String memberToWeixin(Long appId) {
        return "会员调用微信服务：" + weixinServiceFeign.appInfo(appId);
    }

    @Override
    public String getConfig() {
        return env;
    }

}
