package com.liuwq;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2Doc
@MapperScan("com.liuwq.service.member.mapper")
@EnableAsync
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class);
    }


}
