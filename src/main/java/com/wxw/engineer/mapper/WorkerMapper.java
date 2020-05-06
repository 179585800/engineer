package com.wxw.engineer.mapper;

import com.wxw.engineer.config.service.User;
import com.wxw.engineer.entity.Workers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface WorkerMapper {


    @Select("select a.name locationName, t.* from workers t  , work_location a where a.id=t.work_location and t.user_id=#{userId} order by t.status desc")
    public List<Workers> findAllByUserId(String userId);
    @Select("select * from workers t where t.id=#{id} order by t.status desc")
    public Workers findById(Long id);
    @Select("select * from workers t where t.user_id=#{userId}  and t.name = #{name} order by t.status desc")
    public List<Workers> findByNameAndUserId(String name,String userId);

    @Update("update workers t set t.name=#{name},t.category=#{category},t.salary=#{salary}, " +
            "t.status=#{status},t.work_location=#{workLocation},update_time=now() " +
            "where  t.id=#{id} ")
    public int updateWorker(Workers workers);
    @Insert("insert into workers (user_id,name,category,salary,status,work_location,create_time,update_time) " +
            "values (#{userId},#{name},#{category},#{salary},#{status},#{workLocation},NOW(),NOW())")
    public int save(Workers workers);
}