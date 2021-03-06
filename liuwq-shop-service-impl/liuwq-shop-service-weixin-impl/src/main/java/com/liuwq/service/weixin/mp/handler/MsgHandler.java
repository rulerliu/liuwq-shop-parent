package com.liuwq.service.weixin.mp.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liuwq.service.weixin.entity.WechatKeyword;
import com.liuwq.service.weixin.mapper.KeywordMapper;
import com.liuwq.service.weixin.mp.builder.TextBuilder;
import com.liuwq.shop.common.util.HttpClientUtils;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@RefreshScope
public class MsgHandler extends AbstractHandler {

    @Value("${wx.defaultMsg}")
    private String defaultMsg;

    @Value("${wx.rpcWeatherUrl}")
    private String rpcWeatherUrl;
    /**
     * 心知天气接口：https://www.seniverse.com/
     */
    @Autowired
    private KeywordMapper keywordMapper;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }
        String content = wxMessage.getContent();
        //1.先匹配数据库
        WechatKeyword wechatKeyword = keywordMapper.findByKeyword(content);
        if (wechatKeyword != null) {
            String keywordValue = wechatKeyword.getKeywordValue();
            return new TextBuilder().build(StringUtils.isEmpty(keywordValue) ? defaultMsg
                    : keywordValue, wxMessage, weixinService);
        }
        //2.调用第三方天气预报接口查询
        String realWeatherUrl = rpcWeatherUrl.replace("#location", content);
        JSONObject resultJsonObject = HttpClientUtils.httpGet(realWeatherUrl);
        if (resultJsonObject != null) {
            JSONArray results = resultJsonObject.getJSONArray("results");
            JSONObject resultsZeroJSONObject = results.getJSONObject(0);
            JSONObject locationJSONObject = resultsZeroJSONObject.getJSONObject("location");
            // 地址
            String path = locationJSONObject.getString("path");
            JSONObject nowJSONObject = resultsZeroJSONObject.getJSONObject("now");

            String text = nowJSONObject.getString("text");
            String temperature = nowJSONObject.getString("temperature");
            String lastUpdate = resultsZeroJSONObject.getString("last_update");
            String resultMsg = "您当前查询的城市" + content + ",天气为" + text + "天、实时温度为:" + temperature + "℃，" +
                    "最后更新的时间为:" + lastUpdate;
            return new TextBuilder().build(resultMsg, wxMessage, weixinService);
        }
        //3.回复默认消息
        return new TextBuilder().build(defaultMsg, wxMessage, weixinService);
    }

}
