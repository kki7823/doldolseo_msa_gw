package com.doldolseo.doldolseo_msa_gw.configuration;

import com.doldolseo.doldolseo_msa_gw.filter.AuthenticationFilter;
import com.doldolseo.doldolseo_msa_gw.filter.TestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {
    @Autowired
    AuthenticationFilter authFilter;
    @Autowired
    TestFilter testFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("doldolseo-area", r -> r.path("/doldolseo/area/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8081/"))

                .route("doldolseo-member", r -> r.path("/doldolseo/member/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8080/"))

                .route("doldolseo-review", r -> r.path("/doldolseo/review/**")
                        .filters(f -> f.filter(testFilter))
                        .uri("http://localhost:8090/"))

                .build();
    }
}
