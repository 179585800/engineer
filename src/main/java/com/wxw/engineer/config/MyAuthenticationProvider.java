/*
package com.wxw.engineer.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wxw.engineer.config.service.PasswordService;
import com.wxw.engineer.config.service.UserServiceDetailImpl;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;


@Component
public class MyAuthenticationProvider extends DaoAuthenticationProvider
{


    */
/**
     * 规则校验
     *//*

    @Resource(name = "passwordService")
    private PasswordService passwordService;

//    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    // 构造函数中注入
    */
/*@Inject*//*

    public MyAuthenticationProvider(UserServiceDetailImpl userDetailsService)
    {
        this.setUserDetailsService(userDetailsService);
    }

    */
/**
     * 自定义验证方式
     *//*

    @Override
    public Authentication authenticate(Authentication authentication)
    {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = (UserDetails)
                this.getUserDetailsService().loadUserByUsername(username);

        //按登录规则校验用户
//        boolean flag = passwordService.validateRules(userDetails, password);
//        if (!flag)
//        {
//            throw new BadCredentialsException(
//                    "用户名密码不匹配");
//        }
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(JSON.toJSONString(userDetails, SerializerFeature.WriteMapNullValue), password, authorities);
        return authenticationToken;

    }

    @Override
    public boolean supports(Class<?> arg0)
    {
        return true;
    }

}*/
