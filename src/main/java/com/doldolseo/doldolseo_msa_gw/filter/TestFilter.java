package com.doldolseo.doldolseo_msa_gw.filter;

import com.doldolseo.doldolseo_msa_gw.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TestFilter implements GatewayFilter {
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        System.out.println("메소드 "+request.getMethodValue());
//        HttpCookie cookie = request.getCookies().get("token").get(0);
//        final String token = cookie.toString().substring(6);
//
//        System.out.println("token : " + token);
//
//        String id = jwtUtil.getIdFromToken(token);
//        Boolean expDate = jwtUtil.isTokenExpired(token);
//
//        System.out.println("추출한 id : " + id);
//        System.out.println(expDate);


        return chain.filter(exchange);
    }
}
