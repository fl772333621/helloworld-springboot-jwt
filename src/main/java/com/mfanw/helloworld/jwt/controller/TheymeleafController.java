package com.mfanw.helloworld.jwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author mengwei
 */
@Controller
public class TheymeleafController {

    /**
     * 将/error请求内容展示到 /templates/error.html 上
     *
     * @return 页面
     */
    @GetMapping("/error")
    public String error() {
        return "error";
    }

    /**
     * 将/success请求内容展示到 /templates/success.html 上
     *
     * @return 页面
     */
    @GetMapping("/success")
    public String success() {
        return "success";
    }

    /**
     * 将/hello请求内容展示到 /templates/hello.html 上
     *
     * @return 页面
     */
    @GetMapping("/hello")
    public String hello() {
        return "success";
    }
}
