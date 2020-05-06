package com.wxw.engineer.entity;

import com.wxw.engineer.util.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Author
 * @Date 2019-09-20
 */

@Data

public class WorkDetail extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = 2578168923185277491L;


    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "days", precision = 3, scale = 1)
    private BigDecimal days;

    @Column(name = "worker_id")
    private Long workerId;
    /**
     * 1、有效0无效
     */
    @Column(name = "status")
    private String status;
    private String workDate;
    @Transient
    private Long detailId;

    private  String workLocation;


}
