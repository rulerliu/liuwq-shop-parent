package com.liuwq.service.member.impl;

import com.alibaba.fastjson.JSONObject;
import com.liuwq.service.member.api.MemberLoginService;
import com.liuwq.service.member.dto.req.UserLoginReqDto;
import com.liuwq.service.member.entitydo.UserDo;
import com.liuwq.service.member.manage.AsyncLoginLogManage;
import com.liuwq.service.member.mapper.UserMapper;
import com.liuwq.shop.common.util.MD5Util;
import com.liuwq.shop.common.util.TokenUtils;
import liuwq.shop.service.base.BaseApiService;
import liuwq.shop.service.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class MemberLoginServiceImpl extends BaseApiService implements MemberLoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AsyncLoginLogManage asyncLoginLogManage;

    @Override
    public BaseResponse<JSONObject> login(UserLoginReqDto userLoginReqDto, String ipAddress) {
        String mobile = userLoginReqDto.getMobile();
        String passWord = userLoginReqDto.getPassWord();
        String newPassword = MD5Util.MD5(passWord);
        UserDo userDo = userMapper.login(mobile, newPassword);
        if (userDo == null) {
            return setResultError("手机号码或密码不正确");
        }

        Long userId = userDo.getUserId();
        String userToken = tokenUtils.createToken("user_token", userId + "");
        JSONObject data = new JSONObject();
        data.put("userToken", userToken);

        // 写入日志
        // org.springframework.core.task.TaskRejectedException: Executor [java.util.concurrent.ThreadPoolExecutor@34b9b3ed[Running, pool size = 2, active threads = 2, queued tasks = 10, completed tasks = 0]] did not accept task: org.springframework.aop.interceptor.AsyncExecutionInterceptor$$Lambda$673/395774675@73ea7eb2
//        for (int i = 0; i < 13; i++) {
//        for (int i = 0; i < 12; i++) {
        log.info(Thread.currentThread().getName() + " 处理流程1");
        asyncLoginLogManage.loginLog(userId, ipAddress, new Date(), userToken
                , "PC", "windows 谷歌浏览器");
        log.info(Thread.currentThread().getName() + " 处理流程3");
//        }

        return setResultSuccess(data);
    }

//    AbstractHandlerMethodMapping

    // 注意：放在同一个类里面，异步会失效。。。放在controller里面，会导致controller里面所有接口全部404
    // 类不实现接口，controller会注册用cglib到spring mvc容器，不会出现接口404，异步会失效，可以从spring容器获取bean对象调用异步方法实现异步
    // 类实现接口，controller不会注册到spring mvc容器，访问类里面所有接口404
    /*@Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Async  // @Async注解原理：把当前类生成一个动态代理类，去异步执行
    public void loginLog(Long userId, String loginIp, Date loginTime, String loginToken, String channel,
                         String equipment) {
        UserLoginLogDo userLoginLogDo = new UserLoginLogDo(userId, loginIp, loginTime, loginToken, channel, equipment);
        log.info(Thread.currentThread().getName() + ",userLoginLogDo:" + userLoginLogDo.toString() + ",流程2");
        userLoginLogMapper.insertUserLoginLog(userLoginLogDo);
        log.info(Thread.currentThread().getName() + " 处理流程2");
    }*/

}
