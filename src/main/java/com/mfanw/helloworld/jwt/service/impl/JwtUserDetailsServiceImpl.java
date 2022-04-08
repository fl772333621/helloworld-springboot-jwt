package com.mfanw.helloworld.jwt.service.impl;

import com.mfanw.helloworld.jwt.configuration.jwt.SecurityUserDetails;
import com.mfanw.helloworld.jwt.service.JwtUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengwei
 */
@Slf4j
@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailService {

    @Override
    public SecurityUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.isEmpty()) {
            return null;
        }
        // 模拟数据库查询：假设一个条件，证明用户存在
        if (username.contains("a") && username.length() < 10) {
            SecurityUserDetails result = new SecurityUserDetails();
            result.setId(username.hashCode());
            result.setUsername(username);
            result.setPassword("password_" + username);
            result.setAddress("address_" + username);
            result.setSex(0);
            List<GrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
            result.setAuthorities(authorityList);
            return result;
        }
        return null;
    }

}