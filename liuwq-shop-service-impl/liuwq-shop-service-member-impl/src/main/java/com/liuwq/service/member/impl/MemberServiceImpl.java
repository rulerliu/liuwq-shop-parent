package com.liuwq.service.member.impl;

import com.liuwq.service.member.api.MemberService;
import com.liuwq.service.member.dto.resp.UserRespDto;
import com.liuwq.service.member.entitydo.UserDo;
import com.liuwq.service.member.feign.WeixinServiceFeign;
import com.liuwq.service.member.mapper.UserMapper;
import com.liuwq.shop.common.util.DesensitizationUtil;
import com.liuwq.shop.common.util.TokenUtils;
import liuwq.shop.service.base.BaseApiService;
import liuwq.shop.service.base.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@RefreshScope // 加上这个注解实时刷新配置文件
@Service
public class MemberServiceImpl extends BaseApiService implements MemberService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private WeixinServiceFeign weixinServiceFeign;
    @Value("${project.env}")
    private String env;

    public String memberToWeixin(Long appId) {
        return "会员调用微信服务：" + weixinServiceFeign.appInfo(appId);
    }

    @Override
    public String getConfig() {
        return env;
    }

    @Override
    public BaseResponse<UserRespDto> getMemberInfo(String userToken) {
        if (StringUtils.isEmpty(userToken)) {
            return setResultError("token不能为空");
        }
        String userIdStr = tokenUtils.getTokenValue(userToken);
        if (StringUtils.isEmpty(userIdStr)) {
            return setResultError("token无效，或者已经过期");
        }
        Long userIdl = Long.parseLong(userIdStr);
        UserDo userDo = userMapper.findByUser(userIdl);
        if (userDo == null) {
            return setResultError("token无效，或者已经过期");
        }
        UserRespDto userRespDto = doToDto(userDo, UserRespDto.class);
        String newmobile = userRespDto.getMobile();
        // 手机号码脱敏
        userRespDto.setMobile(DesensitizationUtil.mobileEncrypt(newmobile));
        return setResultSuccess(userRespDto);
    }

}
