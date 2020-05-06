package com.wxw.engineer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wxw.engineer.util.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description
 * @Author
 * @Date 2019-09-20
 */

@Data

public class Workers extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = 3689201185752276337L;


    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private BigDecimal salary;

    /**
     * 1、有效0无效
     */
    @Column(name = "status")
    private String status;

    private String workLocation;

    /**
     * 工种
     */
    @Column(name = "category")

    private String locationName;
    private String category;
    @Transient
    private String text;
    @Transient
    private boolean checked;
}
