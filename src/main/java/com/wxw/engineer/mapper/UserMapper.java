package com.wxw.engineer.mapper;

import com.wxw.engineer.config.service.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user t where t.id=#{userId}")
    User findByUserId(String userId);
    @Select("select * from user t where t.open_id=#{openId}")
    User findByOpenId(String openId);
    @Insert("insert into user (open_id,username,create_time,update_time) " +
            "values (#{openId},#{username},NOW(),NOW())")
    public int saveUser(User user);

}