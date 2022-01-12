package com.doldolseo.doldolseo_msa_gw.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TestFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public TestFilter() {
        super(GlobalFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(GlobalFilter.Config config) {
        return ((exchange, chain) -> {
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        System.out.println(config.getBaseMessage());
                        System.out.println("2.테스트필터");
                    })
            );
        });
    }
}
