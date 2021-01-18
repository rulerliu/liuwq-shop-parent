package com.liuwq.service.member.feign;

import com.liuwq.service.weixin.api.WeixinService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("liuwq-weixin")
public interface WeixinServiceFeign extends WeixinService {

    /*@GetMapping("appInfo")
    String appInfo(@RequestParam("userId") Long userId);
*/
}
