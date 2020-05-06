package com.wxw.engineer.controller;

import com.wxw.engineer.annotation.JwtIgnore;
import com.wxw.engineer.config.service.User;
import com.wxw.engineer.model.WeChatLoginModel;
import com.wxw.engineer.service.UserServiceImpl;
import com.wxw.engineer.util.JwtTokenUtil;
import com.wxw.engineer.util.RestResponse;
import com.wxw.engineer.util.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/userController")
public class UserController
{

    @Autowired
    private UserServiceImpl userService;



    @PostMapping("/login")
    public RestResponse<User> login(Map<String, String> userInfo)
    {
        User user = userService.login(userInfo);
        return new RestResponse(RestStatus.OK, user, "");
    }
    @PostMapping("/loginWX")
    @JwtIgnore
    public RestResponse<User> loginWeixin(@RequestBody WeChatLoginModel weChatLoginModel, HttpServletResponse response)
    {

            String token = userService.loginWeixin(weChatLoginModel);
        // 将token放在响应头
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        return new RestResponse(RestStatus.OK, token, "");
    }
    @PostMapping("/reg")

    public RestResponse<String> reg(@RequestBody Map<String, String> user) throws Exception
    {
        userService.reg(user.get("code"), user.get("account"), user.get("password"), user.get("uesername"));
        return new RestResponse(RestStatus.OK, null, "");
    }
}
