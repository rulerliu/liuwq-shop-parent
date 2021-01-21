package com.liuwq.service.weixin.entity;

import lombok.Data;

import java.util.Date;

@Data
public class WechatKeyword {
    private Long id;
    private String keywordName;
    private String keywordValue;
    private Date createTime;
    private Date updateTime;
    private Long version;
}
