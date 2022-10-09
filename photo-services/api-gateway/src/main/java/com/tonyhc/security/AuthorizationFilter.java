package com.tonyhc.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
public class AuthorizationFilter implements WebFilter {
    @Value("${authorization.token.header.name}")
    private String tokenHeader;

    @Value("${authorization.token.header.prefix}")
    private String tokenHeaderPrefix;

    @Value("${token.secret}")
    private String tokenSecret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        List<String> authorizationHeader = exchange.getRequest().getHeaders().get(tokenHeader);
        Object result = null;

        if (authorizationHeader != null) {
            result = (authorizationHeader.size() == 1 ? authorizationHeader.get(0) : authorizationHeader);
        }

        if (result == null) {
            return chain.filter(exchange);
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(exchange.getRequest()).block();

        return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
    }

    private Mono<UsernamePasswordAuthenticationToken> getAuthentication(ServerHttpRequest request) {
        List<String> authorizationHeader = request.getHeaders().get(tokenHeader);
        Object result = null;

        if (authorizationHeader != null) {
            result = (authorizationHeader.size() == 1 ? authorizationHeader.get(0) : authorizationHeader);
        }

        if (result == null || !result.toString().startsWith(tokenHeaderPrefix)) {
            return null;
        }

        String token = result.toString().replace(tokenHeaderPrefix, "");

        String userId = (String) Jwts.parser()
                .setSigningKey(tokenSecret)
                .parseClaimsJws(token)
                .getBody()
                .get("id");

        if (userId == null) {
            return null;
        }

        return Mono.just(new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList()));
    }
}