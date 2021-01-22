package com.mfanw.helloworld.jwt.controller;

import com.mfanw.helloworld.jwt.consts.AppConsts;
import com.mfanw.helloworld.jwt.dto.UserDto;
import com.mfanw.helloworld.jwt.util.JwtUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Hello Controller
 *
 * @author mengwei
 */
@Controller
public class HelloController {

    /**
     * @param model
     * @param response
     * @param username
     * @param password
     * @return
     */
    @PostMapping(path = "/doLogin")
    public String doLogin(Model model, HttpServletResponse response, String username, String password) {
        if (username != null && username.equals(password)) {
            UserDto userDto = new UserDto();
            userDto.setUserId(username);
            userDto.setUsername(username);
            userDto.setPassword(password);
            userDto.setToken(JwtUtils.createToken(username, username, password));
            model.addAttribute("userDto", userDto);
            response.setHeader(AppConsts.JWT_HEADER_NAME, userDto.getToken());
            return "success";
        }
        model.addAttribute("message", "登陆失败");

        return "error";
    }

    @GetMapping("sayHello")
    public String sayHello(Model model) {
        model.addAttribute("address", "北京市朝阳区来广营");
        model.addAttribute("message", "今天天气很好，玩的开心~");
        return "hello";
    }
}
