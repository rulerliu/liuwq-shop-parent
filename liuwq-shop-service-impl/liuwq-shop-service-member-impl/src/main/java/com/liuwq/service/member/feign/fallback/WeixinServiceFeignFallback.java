package com.liuwq.service.member.feign.fallback;

import com.liuwq.service.member.feign.WeixinServiceFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WeixinServiceFeignFallback implements FallbackFactory<WeixinServiceFeign> {

    @Override
    public WeixinServiceFeign create(Throwable throwable) {
        System.out.println("skdlfjskf");
        log.error("WeixinServiceFeign服务调用异常", throwable);
        return new WeixinServiceFeign(){
            @Override
            public String appInfo(Long appId) {
                return "appInfo服务降级";
            }

        };
    }

}
