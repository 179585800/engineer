package com.wxw.engineer.entity;

import com.wxw.engineer.util.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description
 * @Author
 * @Date 2019-09-20
 */

@Data
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = 4405198054532798975L;


    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    /**
     * 1、有效0无效
     */
    @Column(name = "status")
    private Long status;

    @Column(name = "salary")
    private Double salary;

}
