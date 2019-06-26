package com.xiaobai.config;

import com.xiaobai.sys.service.UserDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailInfo userService;

    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        System.out.println("前端传过来的明文密码:" + password);
        BCryptPasswordEncoder bcryptPasswordEncoder=new BCryptPasswordEncoder();
        String encodePassword = bcryptPasswordEncoder.encode(password);
        UserDetails user = userService.loadUserByUsername(username);
        System.out.println("加密后的密码:" + encodePassword);
        System.out.println("数据库的密码:" +  user.getPassword());

        if (!bcryptPasswordEncoder.matches(password,user.getPassword())) {
            throw new DisabledException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
