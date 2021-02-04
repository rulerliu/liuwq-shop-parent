package com.liuwq.service.member.api;

import com.alibaba.fastjson.JSONObject;
import com.liuwq.service.member.dto.req.UserLoginReqDto;
import liuwq.shop.service.base.BaseResponse;

public interface MemberLoginService {

    /**
     * 会员登录接口
     *
     * @param userLoginReqDto
     * @return
     */
    BaseResponse<JSONObject> login(UserLoginReqDto userLoginReqDto);

}
