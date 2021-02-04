package com.liuwq.service.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.liuwq.service.member.api.MemberLoginService;
import com.liuwq.service.member.dto.req.UserLoginReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liuwq.shop.service.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "会员登录服务接口")
public class MemberLoginController {

    @Autowired
    private MemberLoginService memberLoginService;

    @ApiOperation("会员登录")
    @PostMapping("/login")
    BaseResponse<JSONObject> login(@Validated @RequestBody UserLoginReqDto userLoginReqDto, BindingResult bindingResult,
                                   @RequestHeader("X-Real-IP") String ipAddress) {
        return memberLoginService.login(userLoginReqDto, ipAddress);
    }

}
