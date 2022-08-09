/*
    When using Spring Cloud API Gateway, any microservice registered with Eureka Server
    containing multiple instances will automatically be load balanced.

    No configuration file is required for load balancing a specific microservice when using
    Spring Cloud API Gateway.

    package com.springboot.studentservice.feignclients;

    import feign.Feign;
    import org.springframework.cloud.client.loadbalancer.LoadBalanced;
    import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
    import org.springframework.context.annotation.Bean;

    @LoadBalancerClient(value = "address-service")
    public class AddressServiceLoadBalancerConfig {
        @LoadBalanced
        @Bean
        public Feign.Builder feignBuilder() {
            return Feign.builder();
        }
    }
*/