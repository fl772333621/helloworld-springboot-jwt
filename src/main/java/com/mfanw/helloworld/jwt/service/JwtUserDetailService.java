package com.mfanw.helloworld.jwt.service;

import com.mfanw.helloworld.jwt.configuration.jwt.SecurityUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用户信息查询服务
 *
 * @author mengwei18
 */
public interface JwtUserDetailService extends UserDetailsService {

    /**
     * 从数据库中根据 username 查询用户信息表
     *
     * @param username 用户登录名
     * @return 用户信息
     */
    @Override
    SecurityUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
