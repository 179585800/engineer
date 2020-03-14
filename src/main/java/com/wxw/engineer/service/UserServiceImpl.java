package com.wxw.engineer.service;

import com.alibaba.fastjson.JSONObject;
import com.wxw.engineer.config.service.User;
import com.wxw.engineer.config.service.UserDao;
import com.wxw.engineer.util.CryptoUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Log4j2
public class UserServiceImpl
{
    @Autowired
    private UserDao userDao;

    private final static String appid = "wx0acf2e528a4cff7e";
    private final static String secret = "0e34c94a0dca23686dbba43ce155f7f8";

    public User login(String code, String name)
    {
        RestTemplate restTemplate = new RestTemplate();// 发送request请求
        String params = "appid=" + appid + "&secret=" + secret + "&js_code=" + code;//参数
        String url = "https://api.weixin.qq.com/sns/jscode2session?" + params;// 微信接口 用于查询oponid
        String response = restTemplate.getForObject(url, String.class);
        log.info("response:" + response);
        JSONObject json = JSONObject.parseObject(response);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        WeixinRespense weixinRespense = objectMapper.readValue(response); // 逆序列化 ，将字符串中的有效信息取出
//        String session_key = weixinRespense.getSession_key();//如果解密encryptData获取unionId，会用的到
//        openid= weixinRespense.getOpenid();//微信小程序 用户唯一标识
        log.info("session_key:" + json.getString("session_key"));
        log.info("openid:" + json.getString("openid"));
        String openid = json.getString("openid");
//        // 注册用户，将查询到的oponid作为id
        User user = userDao.findByUserId(openid);

        if (null == user)
        {
            user = new User();
            user.setUserId(openid);
            user.setUsername(name);
            userDao.save(user);
            return userDao.findByUserId(openid);
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

    public User loginWeixin(Map<String, String> userInfo)
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
        User user = userDao.findByUserId(openId);

        if (null == user)
        {
            user = new User();
            user.setUserId(openId);
            user.setAccount(openId);
            user.setUsername(name);
            userDao.save(user);
            return userDao.findByUserId(openId);
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
        User user = userDao.findByAccount(account);
        if (!"753951".equals(code))
        {
            throw new Exception("邀请码错误");
        }
        if (null == user)
        {
            user = new User();
//            user.setUserId(openid);
            user.setUsername(uesername);
            user.setAccount(account);
            user.setPassword(CryptoUtils.encodeMD5(password));
            userDao.save(user);

        }
        else
        {
            throw new Exception("此用户已注册");
        }

    }

}
