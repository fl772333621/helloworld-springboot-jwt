package com.mfanw.helloworld.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * JWT 测试
 *
 * @author mengwei
 */
@RestController
@SpringBootApplication
public class JwtApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
        LOGGER.warn("启用 SpringBoot Security, 默认登陆用户为 user, 默认登陆密码为日志(往上翻)中的 Using generated security password:XXX");
    }

}