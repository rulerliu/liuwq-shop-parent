package com.liuwq.service.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.liuwq.service.member.api.MemberRegisterService;
import com.liuwq.service.member.dto.req.UserRegisterReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liuwq.shop.service.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "会员注册服务接口")
public class MemberRegisterController {

    @Autowired
    private MemberRegisterService memberRegisterService;

    @ApiOperation("会员注册")
    @PostMapping("/register")
    BaseResponse<JSONObject> register(@Validated @RequestBody UserRegisterReqDto userRegisterReqDto, BindingResult bindingResult) {
        return memberRegisterService.register(userRegisterReqDto);
    }

}
