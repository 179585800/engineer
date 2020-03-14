package com.wxw.engineer.dao;

import com.wxw.engineer.entity.Workers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkRepository extends JpaRepository<Workers, Long>
{
    public List<Workers> findAllByStatusAndUserId(String status, String userId);

    public List<Workers> findAllByUserIdOrderByStatusDesc(String userId);

    @Override
    public Optional<Workers> findById(Long id);

    public List<Workers> findByNameAndUserId(String name, String userId);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update Workers t set t.name=:name,t.category=:category,t.salary=:salary, t.status=:status where  t.id=:id ")
    public int updateWorker(Long id, String name, String category, BigDecimal salary, String status);

}
