package com.liuwq.service.weixin.mapper;

import com.liuwq.service.weixin.entity.WechatKeyword;
import org.apache.ibatis.annotations.Select;

public interface KeywordMapper {

    @Select("SELECT  id as id ,keyword_name as keywordname,\n" +
            "keyword_value as keywordvalue,create_time as createtime,\n" +
            "update_time as updatetime ,version  as version\n" +
            "  FROM wechat_keywords where keyword_name=#{keyword};")
    WechatKeyword findByKeyword(String keyword);
}
