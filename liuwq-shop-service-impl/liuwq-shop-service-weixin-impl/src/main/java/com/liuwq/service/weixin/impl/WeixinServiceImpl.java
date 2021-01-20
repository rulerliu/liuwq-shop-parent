package com.liuwq.service.weixin.impl;

import com.liuwq.service.weixin.api.WeixinService;
import liuwq.shop.service.base.BaseApiService;
import liuwq.shop.service.base.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class WeixinServiceImpl extends BaseApiService implements WeixinService {
    @Value("${project.env}")
    private String env;

    @Override
    public String appInfo(Long appId) {
        return "appId:" + appId;
    }

    @Override
    public String getConfig() {
        return env;
    }

    @Override
    public BaseResponse<String> addApp(String appId, String appName) {
        if (StringUtils.isEmpty(appId)) {
            return setResultError("appId不能为空");
        }
        if (StringUtils.isEmpty(appName)) {
            return setResultError("appName不能为空");
        }
        return setResultSuccess("appId" + "---" + appName);
    }
}
