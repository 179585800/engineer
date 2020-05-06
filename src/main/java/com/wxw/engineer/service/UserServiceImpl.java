package com.wxw.engineer.service;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.javafx.collections.MappingChange;
import com.wxw.engineer.config.service.User;
import com.wxw.engineer.mapper.UserMapper;
import com.wxw.engineer.model.Audience;
import com.wxw.engineer.model.WeChatLoginModel;
import com.wxw.engineer.util.CryptoUtils;
import com.wxw.engineer.util.JwtTokenUtil;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl
{
    @Autowired
    private Audience audience;

    @Autowired
    private UserMapper userMapper;
    private final static String appid = "wx0acf2e528a4cff7e";
    private final static String secret = "0e34c94a0dca23686dbba43ce155f7f8";

    @Transactional(rollbackFor = Exception.class)
    public String loginWeixin(WeChatLoginModel weChatLoginModel)
    {
        RestTemplate restTemplate = new RestTemplate();// 发送request请求
        String params = "appid=" + appid + "&secret=" + secret + "&js_code=" + weChatLoginModel.getCode();//参数
        String url = "https://api.weixin.qq.com/sns/jscode2session?" + params;// 微信接口 用于查询oponid
        String response = restTemplate.getForObject(url, String.class);
        log.info("response:" + response);
        JSONObject json=JSONObject.parseObject(response);
//        Map<String,String> map = null;
//        try
//        {
//            map = jsonMapper.readValue(response, Map.class);
//        }
//        catch (JsonProcessingException e)
//        {
//            e.printStackTrace();
//        }
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        WeixinRespense weixinRespense = objectMapper.readValue(response); // 逆序列化 ，将字符串中的有效信息取出
//        String session_key = weixinRespense.getSession_key();//如果解密encryptData获取unionId，会用的到
//        openid= weixinRespense.getOpenid();//微信小程序 用户唯一标识
        log.info("session_key={}" , json.get("session_key"));
        log.info("openid={}" , json.get("openid"));
        String openid = json.getString("openid");
//        // 注册用户，将查询到的oponid作为userid
        User user = userMapper.findByOpenId(openid);

        if (null == user)
        {
            user = new User();
            user.setOpenId(openid);
           user.setUsername(weChatLoginModel.getName());
            userMapper.saveUser(user);
            user= userMapper.findByOpenId(openid);
        }

        Map<String,Object> map=new HashMap<>();
        map.put("userId",user.getId());
        map.put("userName",user.getUsername());
        map.put("openId",user.getOpenId());
      String  token= JwtTokenUtil.createJWT(map,audience);
        log.info("token={}" , token);
        return  token;
        /*User u = new User();
        u.setId(user.getId());
        u.setPassword(user.getPassword() == null ? user.getUserId() : user.getPassword());
        u.setSessionKey(map.get("session_key"));
        String token = getToken(u);*/
//        result.setToken(token);
//        result.setCode(1);
//        result.setMessage("登陆成功");

//        else {
//            if (userService.signupUser(user) == 1)
//            {
//                return userService.getUserById(openid);
//            }
//            else {
//                return null;
//            }
//        }


//        return  json;
    }
    protected String getToken(User user) {
        String token="";
        token= JWT.create()
                .withKeyId(user.getUserId())
                .withIssuer("www.ikertimes.com")
                .withIssuedAt(new Date())
                .withJWTId("jwt.ikertimes.com")
                .withClaim("session_key", user.getSessionKey())
                .withAudience(user.getUserId())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public User login(Map<String, String> userInfo)
    {
//        RestTemplate restTemplate = new RestTemplate();// 发送request请求
//        String params = "appid=" + appid + "&secret=" + secret + "&js_code=" + code;//参数
//        String url = "https://api.weixin.qq.com/sns/jscode2session?" + params;// 微信接口 用于查询oponid
//        String response = restTemplate.getForObject(url, String.class);
//        log.info("response:" + response);
//        JSONObject json = JSONObject.parseObject(response);
////        ObjectMapper objectMapper = new ObjectMapper();
////
////        WeixinRespense weixinRespense = objectMapper.readValue(response); // 逆序列化 ，将字符串中的有效信息取出
////        String session_key = weixinRespense.getSession_key();//如果解密encryptData获取unionId，会用的到
////        openid= weixinRespense.getOpenid();//微信小程序 用户唯一标识
//        log.info("session_key:" + json.getString("session_key"));
//        log.info("openid:" + json.getString("openid"));
        String openId = userInfo.get("openId");
        String name = userInfo.get("nickName");
//        // 注册用户，将查询到的oponid作为id
        User user = userMapper.findByUserId(openId);

        if (null == user)
        {
            user = new User();
            user.setUserId(openId);
            user.setAccount(openId);
            user.setUsername(name);
//            userDao.save(user);
//            return userDao.findByUserId(openId);
            return user;
        }
        else
        {
            return user;
        }
//        else {
//            if (userService.signupUser(user) == 1)
//            {
//                return userService.getUserById(openid);
//            }
//            else {
//                return null;
//            }
//        }


//        return  json;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void reg(String code, String account, String password, String uesername) throws Exception
    {

        // 注册用户，将查询到的oponid作为id
//        User user = userDao.findByAccount(account);
//        if (!"753951".equals(code))
//        {
//            throw new Exception("邀请码错误");
//        }
//        if (null == user)
//        {
//            user = new User();
////            user.setUserId(openid);
//            user.setUsername(uesername);
//            user.setAccount(account);
//            user.setPassword(CryptoUtils.encodeMD5(password));
//            userDao.save(user);
//
//        }
//        else
//        {
//            throw new Exception("此用户已注册");
//        }

    }

}
