package com.wxw.engineer.controller;

import com.wxw.engineer.config.service.User;
import com.wxw.engineer.entity.Workers;
import com.wxw.engineer.service.UserServiceImpl;
import com.wxw.engineer.service.WorkServiceImpl;
import com.wxw.engineer.util.RestResponse;
import com.wxw.engineer.util.RestStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
        User user = userService.loginWeixin(userInfo);
        return new RestResponse(RestStatus.OK, user, "");
    }

    @PostMapping("/reg")

    public RestResponse<String> reg(@RequestBody Map<String, String> user) throws Exception
    {
        userService.reg(user.get("code"), user.get("account"), user.get("password"), user.get("uesername"));
        return new RestResponse(RestStatus.OK, null, "");
    }
}
