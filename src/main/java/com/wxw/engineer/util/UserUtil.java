package com.wxw.engineer.util;

import com.wxw.engineer.config.service.User;

public class UserUtil
{
    public static String getUserId()
    {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken))
//        {
//            User pr = JSONObject.toJavaObject(JSONObject.parseObject((String) authentication.getPrincipal()), User.class);
//            return pr.getId().toString();
//        }
//        else
//        {
            return null;
//        }
    }

    public static User getUser()
    {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken))
//        {
//            User pr = JSONObject.toJavaObject(JSONObject.parseObject((String) authentication.getPrincipal()), User.class);
//            return pr;
//        }
//        else
//        {
            return null;
//        }
    }

    public static String getUserName()
    {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken))
//        {
//            User pr = JSONObject.toJavaObject(JSONObject.parseObject((String) authentication.getPrincipal()), User.class);
//            return pr.getUsername();
//        }
//        else
//        {
            return null;
//        }
    }
}
