package com.liuwq.service.weixin.api;

import liuwq.shop.service.base.BaseResponse;
import org.springframework.web.bind.annotation.RequestParam;

public interface WeixinService {

    /**
     * 注意：这里被feign继承之后，方法参数必须加上@RequestParam注解
     * @param appId
     * @return
     */
    String appInfo(@RequestParam("appId") Long appId);

    BaseResponse<String> addApp(@RequestParam("appId") String appId, @RequestParam("appName") String appName);

    String getConfig();
}
