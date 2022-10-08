package com.tonyhc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.tonyhc.security.SecurityConstants.API_GATEWAY_URL;
import static com.tonyhc.security.SecurityConstants.H2_URL;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin()
                        .and()
                .authorizeRequests()
                .antMatchers(H2_URL).permitAll()
                .antMatchers("/**").hasIpAddress(API_GATEWAY_URL);

        return http.build();
    }
}