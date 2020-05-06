//package com.wxw.engineer.config;
//
//import com.wxw.engineer.config.service.MyPasswordEncoder;
//import com.wxw.engineer.config.service.UserServiceDetailImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableAuthorizationServer
//public class OAuth2Config extends AuthorizationServerConfigurerAdapter
//{
//    //认证管理器
//    @Autowired
//
//    private AuthenticationManager authenticationManager;
//    @Qualifier("jwt")
//    @Autowired
//    private TokenStore tokenStore;
//    @Autowired
//    private JwtAccessTokenConverter jwtAccessTokenConverter;
//    //存储链接
//    /*  @Autowired
//      private RedisConnectionFactory connectionFactory;*/
//    @Autowired
//    private DataSource dataSource;
//    //用户信息相关的实现
//    @Autowired
//    private UserServiceDetailImpl userService;
//
//    //    /**
////     * @param
////     * @return TokenStore
////     * @throws
////     * @Title: tokenStore
////     * @Description: 用户验证信息的保存策略，可以存储在内存中，关系型数据库中，redis中
////     */
////    @Bean
////    public TokenStore tokenStore() {
////        //return new RedisTokenStore(connectionFactory);
////        //return new InMemoryTokenStore();
//////        return new JdbcTokenStore(dataSource);
////        return new JwtTokenStore(accessTokenConverter());
//////        return new InMemoryTokenStore();
////    }
////    @Bean
////    public JwtAccessTokenConverter accessTokenConverter() {
////        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
////        converter.setSigningKey("123");
////        return converter;
////    }
//    @Override
//    /**
//     //配置认证规则，那些需要认证那些不需要
//     */
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception
//    {
//        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
////        security.
//        //允许表单验证，浏览器直接发送post请求即可获取tocken
//        security.allowFormAuthenticationForClients().passwordEncoder(new MyPasswordEncoder());
////        super.configure(security);
//    }
//
//    @Override
//    /**
//     *  //配置认证管理器以及用户信息业务实现
//     */
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
//    {
//        endpoints.authenticationManager(authenticationManager).
//                userDetailsService(userService)
//                .tokenStore(tokenStore)
//                .accessTokenConverter(jwtAccessTokenConverter);
//        // 配置TokenServices参数 可以考虑使用[DefaultTokenServices]，它使用随机值创建令牌
////        DefaultTokenServices tokenServices = new DefaultTokenServices();
////        tokenServices.setTokenStore(endpoints.getTokenStore());
////        tokenServices.setSupportRefreshToken(true);
////        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
////        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
////        tokenServices.setAccessTokenValiditySeconds( (int) TimeUnit.DAYS.toSeconds(30)); // 30天
////        endpoints.tokenServices(tokenServices);
//    }
//
//    @Override
//    //   /**
//    //     *
//    //     * 这个方法主要是用于校验注册的第三方客户端的信息，可以存储在数据库中，默认方式是存储在内存中，如下所示，注释掉的代码即为内存中存储的方式
//    //     */
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
//    {
//        clients.inMemory()
//                .withClient("app")//客户端账户
//                .secret(new MyPasswordEncoder().encode("123456"))//客户端密码
//                .scopes("app")
////               .autoApprove(true).
//                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
////               .resourceIds("api")
//                .accessTokenValiditySeconds(-1)
//                .refreshTokenValiditySeconds(60 * 60 * 24)
//                .redirectUris();
////        clients.withClientDetails(clientDetails());
////        clients.jdbc(dataSource);
//    }
//}
