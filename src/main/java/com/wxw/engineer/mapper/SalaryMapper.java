package com.wxw.engineer.mapper;

import com.wxw.engineer.entity.Salary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SalaryMapper {

    @Insert("insert into work_salary(user_id,salary,status,provide_time,create_time,worker_id,work_location) " +
            "values (#{userId},#{salary},'1',#{provideTime},now(),#{workerId},#{workLocation})")
    public void saveSalary(Salary salary) throws Exception;

    @Select(" <script> SELECT\n" +
            "a.id as worker_id,\n" +
            "	a. NAME as name,\n" +
            "	sum(b.salary) AS salary	\n" +
            "FROM\n" +
            "	workers a,\n" +
            "	work_salary b\n" +
            "WHERE a.id = b.worker_id\n" +
            "AND b.provide_time &gt;=#{startDate}\n" +
            "AND b.provide_time &lt;=#{endDate}\n" +
            "AND a.user_id =#{userId}\n" +
            "<if  test=\"name != null and name!=''\">and a.name=#{name}</if>" +
            "AND b. STATUS = '1'\n" +
            "group by a.id,a.name </script>")
    public List<Map<String, Object>> queryTotalSalary(String startDate, String endDate, String name, String userId);

    @Select("<script>" +" SELECT\n" +
            "a.id as worker_id,\n" +
            "	a. NAME as name,\n" +
            "	b.salary AS salary,	\n" +

            "	b.provide_time  AS createTime	\n" +
            "FROM\n" +
            "	workers a,\n" +
            "	work_salary b \n" +
            "WHERE\n" +
            "	a.id = b.worker_id \n" +
            "AND b.provide_time &gt;=#{startDate}\n" +
            "AND b.provide_time &lt;=#{endDate}\n" +
            "AND a.user_id =#{userId}\n" +
            "<if  test=\"name != null and name!='' \">and a.name=#{name}</if>" +
            "AND b. STATUS = '1'\n" +
            "order by b.create_time desc "+"</script>" )
    public List<Map<String,Object>> queryDetail(String startDate, String endDate, @Param("name") String name, String userId);

}
