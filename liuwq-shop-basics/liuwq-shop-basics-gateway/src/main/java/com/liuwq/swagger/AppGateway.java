package com.liuwq.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppGateway {
    /**
     * Gateway的底层是基于webfux
     * SpringMVC底层基于我们的servet实现
     * Spring-web封装了我们SpringMVC
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AppGateway.class);
    }
}
