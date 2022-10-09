package com.tonyhc.security;

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
                .pathMatchers("/user/h2-console/**").permitAll()
                .pathMatchers(HttpMethod.POST,"/user/api/v1/users").permitAll()
                .pathMatchers(HttpMethod.POST, "/user/api/v1/users/login").permitAll()
                .anyExchange().authenticated();

        return http.build();
    }
}
