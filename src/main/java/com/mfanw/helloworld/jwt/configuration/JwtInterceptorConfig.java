package com.mfanw.helloworld.jwt.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mfanw.helloworld.jwt.consts.AppConsts;
import com.mfanw.helloworld.jwt.util.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mengwei
 */
@Configuration
public class JwtInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public JwtAuthenticationInterceptor authenticationInterceptor() {
        return new JwtAuthenticationInterceptor();
    }

    static class JwtAuthenticationInterceptor implements HandlerInterceptor {
        // 注入相关权限校验代码 @Autowired AccountService accountService;

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            // 地址过滤
            String uri = request.getRequestURI();
            if ("/doLogin".equals(uri) || "/".equals(uri) || "/error".equals(uri)) {
                return true;
            }
            // 获取 token
            String token = request.getHeader(AppConsts.JWT_HEADER_NAME);// 从 http 请求头中取出 token
            if (token == null) {
                throw new RuntimeException("无token，请重新登录");
            }
            // 获取 token 中的 user id
            String userId;
            try {
                userId = JwtUtils.getAudience(token);
            } catch (JWTDecodeException j) {
                throw new RuntimeException("401");
            }
            // 此处根据用户服务，去查询用户id是否存在
            if (StringUtils.isEmpty(userId)) {
                throw new RuntimeException("用户不存在，请重新登录");
            }
            // 模拟用户的用户名和密码相同，真实场景中，需要根据用户服务查询到该用户id的密码
            String password = userId;
            // 验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(password)).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("401");
            }
            return true;
        }
    }
}
