package com.wxw.engineer.mapper;

import com.wxw.engineer.entity.WorkLocation;
import com.wxw.engineer.entity.Workers;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface WorkerLocationMapper {


    @Select("select * from work_location t where t.user_id=#{userId} order by t.status desc")
    public List<WorkLocation> findAllByUserId(String userId);
    @Select("select * from work_location t where t.id=#{userId} ")
    public WorkLocation findById(String id);

    @Select("select * from work_location t where t.name=#{name}")
    public List<WorkLocation> findByName(String name);
    @Update("update work_location t set  t.status=#{status},t.name=#{name},update_time=now() where  t.id=#{id} ")
    public int updateWorkLocation(WorkLocation workLocation);
    @Insert("insert into  work_location(name,status,user_id,create_time,update_time) values (#{name},#{status},#{userId},NOW(),NOW())")
  //  @Options(useGeneratedKeys = true, keyProperty = "id")
    @Options(useGeneratedKeys=true,keyProperty="id")
    public int save( WorkLocation workLocation);

}