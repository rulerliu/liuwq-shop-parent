package com.liuwq.service.weixin.controller;

import com.liuwq.service.weixin.api.WeixinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import liuwq.shop.service.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "微信服务")
public class WeixinController {

    @Autowired
    private WeixinService weixinService;

    @ApiOperation("appInfo")
    @ApiImplicitParam(name = "appId", value = "appid", required = true)
    @GetMapping("appInfo")
    String appInfo(@RequestParam("appId") Long appId) {
        int i = 1/0;
        return weixinService.appInfo(appId);
    }

    @ApiOperation("addApp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "appid", required = true),
            @ApiImplicitParam(name = "appName", value = "appName", required = true),})
    @GetMapping("addApp")
    BaseResponse<String> addApp(@RequestParam("appId") String appId, @RequestParam("appName") String appName) {
        return weixinService.addApp(appId, appName);
    }

    @ApiOperation("获取配置文件")
    @GetMapping("getConfig")
    String getConfig() {
        return weixinService.getConfig();
    }

}
