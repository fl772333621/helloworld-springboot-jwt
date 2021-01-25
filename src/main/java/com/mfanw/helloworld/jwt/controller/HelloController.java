package com.mfanw.helloworld.jwt.controller;

import com.mfanw.helloworld.jwt.configuration.jwt.JwtUserDetailsServiceImpl;
import com.mfanw.helloworld.jwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Hello Controller
 *
 * @author mengwei
 */
@Controller
public class HelloController {

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request, String username, String password) {
        final UserDetails userDetails = jwtUserDetailsServiceImpl.loadUserByUsername(username);
        if (username.length() != password.length()) {
            return "密码错误，请重新输入！";
        }
        return jwtTokenUtil.generateToken(userDetails);
    }

    @PostMapping("/hello")
    @ResponseBody
    public String hello() {
        return "今天天气很好，玩的开心~";
    }

    @PostMapping("/helloPermitAll")
    @ResponseBody
    public String helloPermitAll() {
        return "hello 陌生人~";
    }
}
