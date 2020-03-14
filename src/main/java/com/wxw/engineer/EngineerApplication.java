package com.wxw.engineer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class EngineerApplication
{

    public static void main(String[] args)
    {

        SpringApplication.run(EngineerApplication.class, args);
    }

//    // 继承SpringBootServletInitializer 实现configure 方便打war 外部服务器部署。
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
//    {
//        return application.sources(EngineerApplication.class);
//    }
}
