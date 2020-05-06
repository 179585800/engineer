package com.wxw.engineer.model;

import lombok.Data;

/**
 * @version v1.0
 * @ProjectName: engineer
 * @ClassName: WeChatLoginModel
 * @Description: TODO(一句话描述该类的功能)
 * @Author: wangxw
 * @Date: 2020/5/6 10:43
 */
@Data
public class WeChatLoginModel
{
    private String code;
    private String name;
}
