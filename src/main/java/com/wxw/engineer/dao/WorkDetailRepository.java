package com.wxw.engineer.dao;

import com.wxw.engineer.entity.WorkDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Repository
public interface WorkDetailRepository extends JpaRepository<WorkDetail, Long>
{
    @Query(value = "SELECT\n" +
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
            "AND a.user_id = :userId\n" +
            "AND NOT EXISTS (\n" +
            "SELECT\n" +
            "1\n" +
            "FROM\n" +
            "work_detail b\n" +
            "WHERE\n" +
            "b.worker_id = a.id and b.status='1' \n" +
            "AND DATE_FORMAT(b.work_date, '%Y-%m-%d') =:createDate\n" +
            ")", nativeQuery = true)
    public List<Map<String, Object>> findModByDateAndUserId(String createDate, String userId);

    @Query(value = "select b.id, a.name,b.salary as salary,a.category,b.days as days,b.create_time,b.update_time,b.worker_id from workers a , work_detail b where a.id=b.worker_id and DATE_FORMAT(b.work_date,'%Y-%m-%d')=:createDate and  a.user_id=:userId and b.status='1'", nativeQuery = true)
    public List<Map<String, Object>> findByDateAndUserId(String createDate, String userId);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update WorkDetail t set t.status='0',updateTime=sysdate() where  t.id=:id ")
    public int deleteWorker(Long id);

    @Query(value = "SELECT\n" +
            "a.id as worker_id,\n" +
            "	a. NAME as name,\n" +
            "	sum(b.salary*b.days) AS salary,	\n" +
            "	sum(b.days) AS days	\n" +
            "FROM\n" +
            "	workers a,\n" +
            "	work_detail b\n" +
            "WHERE\n" +
            "	a.id = b.worker_id\n" +
            "AND DATE_FORMAT(b.work_date, '%Y-%m-%d') >=:startDate\n" +
            "AND DATE_FORMAT(b.work_date, '%Y-%m-%d') <=:endDate\n" +
            "AND a.user_id =:userId\n" +
            "and   IF (:name!='', a.name=:name, 1=1) " +
            "AND b. STATUS = '1'\n" +
            "group by a.id,a.name", nativeQuery = true)
    public List<Map<String, Object>> queryTotalWorkDay(String startDate, String endDate, String name, String userId);

    @Query(value = "SELECT\n" +
            "a.id as worker_id,\n" +
            "	a. NAME as name,\n" +
            "	b.salary AS salary,	\n" +
            "	b.days AS days,	\n" +
            "	DATE_FORMAT(b.work_date, '%Y-%m-%d') AS createTime	\n" +
            "FROM\n" +
            "	workers a,\n" +
            "	work_detail b\n" +
            "WHERE\n" +
            "	a.id = b.worker_id\n" +
            "AND DATE_FORMAT(b.work_date, '%Y-%m-%d') >=:startDate\n" +
            "AND DATE_FORMAT(b.work_date, '%Y-%m-%d') <=:endDate\n" +
            "AND a.user_id =:userId\n" +
            "and  IF (:name!='', a.name=:name, 1=1) " +
            "AND b. STATUS = '1'\n" +
            "order by b.create_time desc", nativeQuery = true)
    public List<Map<String, Object>> queryDetailWorkDay(String startDate, String endDate, String name, String userId);

}
