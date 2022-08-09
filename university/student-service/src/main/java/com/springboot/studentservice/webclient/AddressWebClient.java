package com.springboot.studentservice.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AddressWebClient {
    @Value("${address.service.url}")
    private String addressServiceUrl;

    @Value("${address.service.path}")
    private String addressServicePath;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(addressServiceUrl + addressServicePath).build();
    }
}
