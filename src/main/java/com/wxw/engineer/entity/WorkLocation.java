package com.wxw.engineer.entity;

import com.wxw.engineer.util.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author
 * @Date 2019-09-20
 */

@Data
public class WorkLocation extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = 3689201185752276337L;



    private String name;

    /**
     * 1、有效0无效
     */

    private String status;


}
