package com.mfanw.helloworld.jwt.controller;

import com.mfanw.helloworld.jwt.configuration.jwt.JwtUserDetailsService;
import com.mfanw.helloworld.jwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request, String username, String password) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @GetMapping("helloPermitAll")
    public String helloPermitAll(Model model) {
        model.addAttribute("address", "北京市朝阳区来广营");
        model.addAttribute("message", "今天天气很好，玩的开心~");
        return "hello";
    }

    @GetMapping("sayHello")
    public String sayHello(Model model) {
        model.addAttribute("address", "北京市朝阳区来广营");
        model.addAttribute("message", "今天天气很好，玩的开心~");
        return "hello";
    }
}
