package com.wxw.engineer.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @version v1.0
 * @ProjectName: engineer
 * @ClassName: qw
 * @Description: TODO(一句话描述该类的功能)
 * @Author: wangxw
 * @Date: 2020/5/6 11:28
 */
@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {

    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;

}
