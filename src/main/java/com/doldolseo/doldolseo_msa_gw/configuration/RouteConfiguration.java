package com.doldolseo.doldolseo_msa_gw.configuration;

import com.doldolseo.doldolseo_msa_gw.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {
    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("doldolseo-review", r -> r.path("/doldolseo/review/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8080/"))

                .route("auth-service", r -> r.path("/doldolseo/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8080/"))
                .build();
    }
}
