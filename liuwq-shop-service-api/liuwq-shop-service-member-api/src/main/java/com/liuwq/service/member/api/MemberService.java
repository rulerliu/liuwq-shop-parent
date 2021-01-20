package com.liuwq.service.member.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "会员服务")
public interface MemberService {

    @ApiOperation("会员调用微信")
    @ApiImplicitParam(name = "appId", value = "appid", required = true)
    @GetMapping("memberToWeixin")
    public String memberToWeixin(Long appId);

}
