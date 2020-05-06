package com.wxw.engineer.mapper;

import com.wxw.engineer.entity.WorkDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


@Mapper
public interface WorkDetailMapper
{
    @Select("SELECT\n" +
            "a.id,\n" +
            "'' AS checked,\n" +
            "a. NAME as name,\n" +
            "a. NAME as text,\n" +
            "a.salary AS salary,\n" +
            "a.category\n" +
            "FROM\n" +
            "workers a\n" +
            "WHERE\n" +
            "a. STATUS = '1'\n" +
            "AND a.user_id = #{userId} and a.work_location=#{workLocation}\n" +
            "AND NOT EXISTS (\n" +
            "SELECT\n" +
            "1\n" +
            "FROM\n" +
            "work_detail b\n" +
            "WHERE\n" +
            "b.worker_id = a.id and b.status='1' \n" +
            "AND b.work_date =#{createDate} \n" +
            ")")
    public List<Map<String, Object>> findModByDateAndUserId(String createDate, String userId,String workLocation);

    @Select("select b.id, a.name,b.salary as salary,a.category,b.days as days,b.create_time,b.update_time,b.worker_id from workers a , work_detail b where a.id=b.worker_id and b.work_date=#{createDate} and  a.user_id=#{userId} and b.status='1' and a.work_location=#{workLocation}")
    public List<Map<String, Object>> findByDateAndUserId(String createDate, String userId,String workLocation);
    @Insert("<script>"  +
            "INSERT INTO work_detail(create_time,update_time,user_id,days,salary,status,work_date,worker_id,work_location) VALUES" +
            "<foreach collection='list' item='item1' index='index'  separator=\",\">" +
            "(now(),now(),#{item1.userId},#{item1.days},#{item1.salary},#{item1.status},#{item1.workDate},#{item1.workerId},#{item1.workLocation})" +
            "</foreach>" +
            "</script>")
    public int batchInsert(@Param("list")List<WorkDetail> workDetails);

    @Update("update WorkDetail t set t.status='0',updateTime=now() where  t.id=#{id} ")
    public int deleteWorker(Long id);

    @Select(" <script> SELECT\n" +
            "a.id as worker_id,\n" +
            "	a. NAME as name,\n" +
            "	sum(b.salary*b.days) AS salary,	\n" +
            "	sum(b.days) AS days	\n" +
            "FROM\n" +
            "	workers a,\n" +
            "	work_detail b\n" +
            "WHERE\n" +
            "	a.id = b.worker_id\n" +
            "AND b.work_date &gt;=#{startDate}\n" +
            "AND b.work_date &lt;=#{endDate}\n" +
            "AND a.user_id =#{userId}\n" +
            "<if  test=\"name != null and name!=''\">and a.name=#{name}</if>" +
            "AND b. STATUS = '1'\n" +
            "group by a.id,a.name </script>")
    public List<Map<String, Object>> queryTotalWorkDay(String startDate, String endDate, String name, String userId);
    @Select(" <script> SELECT\n" +
            "a.id as worker_id,\n" +
            "	a. NAME as name,\n" +
            "	sum(b.salary*b.days) AS salary,	\n" +
            "	sum(b.days) AS days	\n" +
            "FROM\n" +
            "	workers a,\n" +
            "	work_detail b\n" +
            "WHERE\n" +
            "	a.id = b.worker_id\n" +
            "AND b.work_date &gt;=#{startDate}\n" +
            "AND b.work_date &lt;=#{endDate}\n" +
            "AND a.user_id =#{userId}\n" +
            "<if  test=\"name != null and name!=''\">and a.name=#{name}</if>" +
            "AND b. STATUS = '1'\n" +
            "group by a.id,a.name </script>")
    public List<Map<String, Object>> queryTotalSalary(String startDate, String endDate, String name, String userId);

    @Select("<script>" +" SELECT\n" +
            "a.id as worker_id,\n" +
            "	a. NAME as name,\n" +
            "	b.salary AS salary,	\n" +
            "	b.days AS days, c.name as locationName,	\n" +
            "	b.work_date  AS createTime	\n" +
            "FROM\n" +
            "	workers a,\n" +
            "	work_detail b ,work_location c\n" +
            "WHERE\n" +
            "	a.id = b.worker_id and b.work_location=c.id \n" +
            "AND b.work_date &gt;=#{startDate}\n" +
            "AND b.work_date &lt;=#{endDate}\n" +
            "AND a.user_id =#{userId}\n" +
            "<if  test=\"name != null and name!='' \">and a.name=#{name}</if>" +
            "AND b. STATUS = '1'\n" +
            "order by b.create_time desc "+"</script>" )
    public List<Map<String,Object>> queryDetailWorkDay(String startDate, String endDate, @Param("name") String name, String userId);

}
