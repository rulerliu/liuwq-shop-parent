package com.liuwq.service.weixin.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import liuwq.shop.service.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "微信服务")
public interface WeixinService {

    /**
     * 注意：这里被feign继承之后，方法参数必须加上@RequestParam注解
     * @param appId
     * @return
     */
    @ApiOperation("appInfo")
    @ApiImplicitParam(name = "appId", value = "appid", required = true)
    @GetMapping("appInfo")
    String appInfo(@RequestParam("appId") Long appId);

    @ApiOperation("addApp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "appid", required = true),
            @ApiImplicitParam(name = "appName", value = "appName", required = true),})
    @GetMapping("addApp")
    BaseResponse<String> addApp(@RequestParam("appId") String appId, @RequestParam("appName") String appName);

}
