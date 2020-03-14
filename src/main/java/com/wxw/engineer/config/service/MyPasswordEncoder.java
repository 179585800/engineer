package com.wxw.engineer.config.service;

import com.wxw.engineer.util.CryptoUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wangxw
 * @Title:
 * @Description: TODO(这里用一句话描述这个方法的作用)
 * @return
 * @throws
 * @Date 2019/10/24 15:16
 */
public class MyPasswordEncoder implements PasswordEncoder
{
    @Override
    public String encode(CharSequence charSequence)
    {
        return CryptoUtils.encodeMD5(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s)
    {

        return s.equalsIgnoreCase(CryptoUtils.encodeMD5(charSequence.toString()));
    }

}
