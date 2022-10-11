package com.tonyhc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AlbumApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(AlbumApplication.class, args);
    }
}
