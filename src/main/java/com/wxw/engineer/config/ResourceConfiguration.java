package com.wxw.engineer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@Configuration
@EnableResourceServer

public class ResourceConfiguration extends ResourceServerConfigurerAdapter
{
    @Autowired
    private DataSource dataSource;
    @Qualifier("jwt")
    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception
    {
        resources.tokenServices(defaultTokenServices());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        // 我们这里放开/order/*的请求，以/order/*开头的请求不用认证
//        http.authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests().antMatchers("/oauth/**")
                .permitAll().antMatchers("/userController/**").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin().permitAll();
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED));

    }

  /*  @Bean
    public TokenStore tokenStores() {
        //return new RedisTokenStore(connectionFactory);
        //return new InMemoryTokenStore();
        return new JwtTokenStore(accessTokenConverter());
//        return new JdbcTokenStore(dataSource);
//        return new InMemoryTokenStore();
    }

    //*
//     * 创建一个默认的资源服务token
//
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }*/

    @Bean
    public ResourceServerTokenServices defaultTokenServices()
    {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        // 使用自定义的Token转换器
//        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        // 使用自定义的tokenStore
        defaultTokenServices.setTokenStore(tokenStore);
//        defaultTokenServices.set
        return defaultTokenServices;
    }

}
