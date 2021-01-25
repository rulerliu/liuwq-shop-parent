package com.liuwq.service.member.impl;

import com.alibaba.fastjson.JSONObject;
import com.liuwq.service.member.api.MemberRegisterService;
import com.liuwq.service.member.dto.req.UserRegisterReqDto;
import com.liuwq.service.member.entitydo.UserDo;
import com.liuwq.service.member.mapper.UserMapper;
import com.liuwq.shop.common.util.MD5Util;
import liuwq.shop.service.base.BaseApiService;
import liuwq.shop.service.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRegisterServiceImpl extends BaseApiService implements MemberRegisterService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse<JSONObject> register(UserRegisterReqDto userRegisterReqDto) {
        // 参数验证
        /*String mobile = userRegisterReqDto.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("mobile参数不能为空");
        }
        String passWord = userRegisterReqDto.getPassWord();
        if (StringUtils.isEmpty(userReqRegisterDto.getPassWord())) {
            return setResultError("passWord参数不能为空");
        }*/
        // 先检查该手机号码是否存在
        UserDo userDbDo = userMapper.existMobile(userRegisterReqDto.getMobile());
        if (userDbDo != null) {
            return setResultError("该手机号码已经存在");
        }
        // dto转换成do
        // 插入数据库中
        UserDo userDo = dtoToDo(userRegisterReqDto, UserDo.class);
        String newPassWord = MD5Util.MD5(userRegisterReqDto.getPassWord());
        userDo.setPassWord(newPassWord);
        //盐 密码+手机号码、提前定义盐值常量
        int register = userMapper.register(userDo);
//        return register > 0 ? setResultSuccess("注册成功") :
//                setResultError("注册失败");
        return setResult(register, "注册成功", "注册失败");
    }
    /**
     * 密码加密 MD5 +加盐值  单加密 解密 破解
     */
}
