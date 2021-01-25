package com.liuwq.service.member.mapper;


import com.liuwq.service.member.entitydo.UserLoginLogDo;
import org.apache.ibatis.annotations.Insert;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: UserLoginLogMapper
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1218:33
 */
public interface UserLoginLogMapper {


    @Insert("\n" +
            "insert into  user_login_log values(null,#{userId},#{loginIp},now(),#{loginToken},#{channel},#{equipment});\n")
    int insertUserLoginLog(UserLoginLogDo userLoginLogDo);
}
