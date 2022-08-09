package com.springboot.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class CustomFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /*
            Pre-filter to log the Authorization value from the request header before API Gateway routes the request.
            This pre-filter triggers for request to any registered microservices in eureka server.
        */
        ServerHttpRequest request = exchange.getRequest();
        log.info("Authorization: " + request.getHeaders().getFirst("Authorization"));

        // Pre-filter riggers for any request to Student Microservice
        if (request.getURI().toString().contains("/api/students/")) {
            log.info("Route the request to the Student Microservice from Spring Cloud API Gateway");
        }

        // Routes request from consumer to appropriate microservice
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            /*
                Post-filter to log the http status code after getting response from API Gateway and before consumer
                receives the response from API Gateway.
                This post-filter triggers for request to any registered microservices in eureka server
            */
            ServerHttpResponse response = exchange.getResponse();
            log.info("Http Status Code: " + response.getStatusCode());
        }));
    }
}