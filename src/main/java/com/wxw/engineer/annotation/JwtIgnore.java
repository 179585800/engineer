package com.wxw.engineer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version v1.0
 * @ProjectName: engineer
 * @ClassName: annotation
 * @Description: TODO(一句话描述该类的功能)
 * @Author: wangxw
 * @Date: 2020/5/6 14:04
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JwtIgnore
{
    boolean required() default true;

}