package com.mfanw.helloworld.jwt.configuration;

import com.mfanw.helloworld.jwt.configuration.jwt.JwtAuthenticationEntryPoint;
import com.mfanw.helloworld.jwt.configuration.jwt.JwtAuthorizationTokenFilter;
import com.mfanw.helloworld.jwt.configuration.jwt.JwtUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * EnableWebSecurity这个注解必须加，开启Security
 * EnableGlobalMethodSecurity(prePostEnabled = true)//保证post之前的注解可以使用
 *
 * @author mengwei
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 存放了Swagger需要加入Spring Security认证的URL
     */
    private static final String[] SWAGGER_URLS = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    private JwtAuthorizationTokenFilter authenticationTokenFilter;

    /**
     * 认证
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsServiceImpl).passwordEncoder(passwordEncoderBean());
    }

    /**
     * 配置拦截专用
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                // 设置/doLogin请求无需登录任何人都可访问
                .antMatchers("/doLogin").permitAll()
                // 设置/helloPermitAll请求无需登录任何人都可访问
                .antMatchers("/helloPermitAll").permitAll()
                // 设置SWAGGER_URLS请求无需登录任何人都可访问
                .antMatchers(SWAGGER_URLS).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                // 剩下所有的验证都需要验证
                .anyRequest().authenticated()
                .and()
                // 禁用 Spring Security 自带的跨域处理
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}