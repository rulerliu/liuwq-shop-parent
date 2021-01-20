package com.liuwq.service.weixin;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2Doc // 注意：这里不要用原生的EnableSwagger2注解
public class WeixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixinApplication.class);
    }


}
