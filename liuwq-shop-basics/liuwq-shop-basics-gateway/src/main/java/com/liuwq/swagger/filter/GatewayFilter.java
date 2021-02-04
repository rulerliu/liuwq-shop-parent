package com.liuwq.swagger.filter;

import com.alibaba.fastjson.JSONObject;
import com.liuwq.swagger.config.SwaggerProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GatewayFilter implements GlobalFilter {

    /**
     * http://gw.mayikt.com/liuwq-member/getMemberInfo 通过nginx访问的会把ip放到请求头中
     * http://127.0.0.1:81/liuwq-member/getMemberInfo
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从我们的nginx 重写请求获取的参数
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        if (StringUtils.endsWithIgnoreCase(path, SwaggerProvider.API_URI)) {
            return chain.filter(exchange);
        }

        String sourceIp = request.getHeaders().getFirst("X-Real-IP");
        if (StringUtils.isEmpty(sourceIp)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", "500");
            jsonObject.put("msg", "sourceIp is null");
            DataBuffer buffer = response.bufferFactory().wrap(jsonObject.toJSONString().getBytes());
            return response.writeWith(Mono.just(buffer));
        }
        // 放行
        return chain.filter(exchange);
    }
}
