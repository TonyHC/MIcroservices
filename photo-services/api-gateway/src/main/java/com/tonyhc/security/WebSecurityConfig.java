package com.tonyhc.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {
    @Value("${actuator.url}")
    private String actuatorURL;

    @Value("${user.actuator.url}")
    private String userActuatorURL;

    @Value("${user.h2-console.url}")
    private String userH2ConsoleURL;

    @Value("${user.create-user.url}")
    private String createUserURL;

    @Value("${user.login.url}")
    private String userLoginURL;

    private final AuthorizationFilter authorizationFilter;

    public WebSecurityConfig(AuthorizationFilter authorizationFilter) {
        this.authorizationFilter = authorizationFilter;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .addFilterAt(authorizationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange()
                .pathMatchers(actuatorURL).permitAll()
                .pathMatchers(userActuatorURL).permitAll()
                .pathMatchers(userH2ConsoleURL).permitAll()
                .pathMatchers(HttpMethod.POST,createUserURL).permitAll()
                .pathMatchers(HttpMethod.POST, userLoginURL).permitAll()
                .anyExchange().authenticated();

        return http.build();
    }
}
