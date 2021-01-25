package com.liuwq.service.member.feign;

import com.liuwq.service.member.config.FeignConfig;
import com.liuwq.service.member.feign.fallback.WeixinServiceFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// fallcack生效需要加上feign.hystrix.enabled=true配置
@FeignClient(name = "liuwq-weixin", fallbackFactory = WeixinServiceFeignFallback.class,
        configuration = FeignConfig.class)
public interface WeixinServiceFeign {

    @GetMapping("appInfo") // 注意：这里必须要加上@RequestParam注解
    String appInfo(@RequestParam("appId") Long appId);

}
