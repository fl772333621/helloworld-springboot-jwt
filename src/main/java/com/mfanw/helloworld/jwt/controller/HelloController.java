package com.mfanw.helloworld.jwt.controller;

import com.mfanw.helloworld.jwt.configuration.jwt.JwtUserDetailsServiceImpl;
import com.mfanw.helloworld.jwt.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "JWT 演示接口")
@Controller
public class HelloController {

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation("登陆请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", required = true, paramType = "query")
    })
    @PostMapping("/doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request, String username, String password) {
        final UserDetails userDetails = jwtUserDetailsServiceImpl.loadUserByUsername(username);
        if (username.length() != password.length()) {
            return "密码错误，请重新输入！";
        }
        return jwtTokenUtil.generateToken(userDetails);
    }

    @ApiOperation("hello请求")
    @PostMapping("/hello")
    @ResponseBody
    public String hello() {
        return "今天天气很好，玩的开心~";
    }

    @ApiOperation("helloPermitAll请求")
    @PostMapping("/helloPermitAll")
    @ResponseBody
    public String helloPermitAll() {
        return "hello 陌生人~";
    }
}
