package com.tonyhc.log;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientLoggerConfig {
    @Bean
    Logger.Level feignClientLoggerLevel() {
        return Logger.Level.FULL;
    }
}