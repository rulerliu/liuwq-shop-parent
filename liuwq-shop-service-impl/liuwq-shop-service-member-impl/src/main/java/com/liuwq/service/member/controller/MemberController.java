package com.liuwq.service.member.controller;

import com.liuwq.service.member.api.MemberService;
import com.liuwq.service.member.dto.resp.UserRespDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import liuwq.shop.service.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "会员服务")
public class MemberController {

    @Autowired
    MemberService memberService;

    @ApiOperation("会员调用微信")
    @ApiImplicitParam(name = "appId", value = "appid", required = true)
    @GetMapping("memberToWeixin")
    public String memberToWeixin(Long appId) {
        return memberService.memberToWeixin(appId);
    }

    @ApiOperation("获取配置")
    @GetMapping("getConfig")
    public String getConfig() {
        return memberService.getConfig();
    }

    @ApiOperation("根据userToken查询用户信息")
    @GetMapping("getMemberInfo")
    public BaseResponse<UserRespDto> getMemberInfo(@RequestParam String userToken) {
        return memberService.getMemberInfo(userToken);
    }

}
