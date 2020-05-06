package com.wxw.engineer.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Salary {
    private Integer userId;
    private BigDecimal salary;
    private String status;
    private String provideTime;
    private Date createTime;
    private Integer workerId;
    private String workLocation;
}
