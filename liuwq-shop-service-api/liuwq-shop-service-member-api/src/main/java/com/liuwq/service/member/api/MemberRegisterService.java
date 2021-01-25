package com.liuwq.service.member.api;

import com.alibaba.fastjson.JSONObject;
import com.liuwq.service.member.dto.req.UserRegisterReqDto;
import liuwq.shop.service.base.BaseResponse;

public interface MemberRegisterService {

    /**
     * 会员注册接口
     *
     * @param userRegisterReqDto
     * @return
     */
    BaseResponse<JSONObject> register(UserRegisterReqDto userRegisterReqDto);

}
