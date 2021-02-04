package com.liuwq.shop.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenUtils {
    @Autowired
    private RedisUtil redisUtil;

    public String createToken(String prefix, String value) {
        return createToken(prefix, value, null);
    }

    /**
     * 前缀
     *
     * @param prefix
     * @param value
     * @return
     */
    public String createToken(String prefix, String value, Long timeOut) {
        //1.生成我们的令牌
        String token = prefix + "_" + UUID.randomUUID().toString().replace("-", "");
        // 2.将该token存入到Redis中
        redisUtil.setString(token, value, timeOut);
        return token;
    }

    public String getTokenValue(String token) {
        return redisUtil.getString(token);
    }

}
