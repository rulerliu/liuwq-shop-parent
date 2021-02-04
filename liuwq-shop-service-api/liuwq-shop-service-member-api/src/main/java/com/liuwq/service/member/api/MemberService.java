package com.liuwq.service.member.api;

import com.liuwq.service.member.dto.resp.UserRespDto;
import liuwq.shop.service.base.BaseResponse;

public interface MemberService {

    String memberToWeixin(Long appId);

    public String getConfig();

    BaseResponse<UserRespDto> getMemberInfo(String userToken);
}
