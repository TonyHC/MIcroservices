package com.tonyhc.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping()
    public String getStatus() {
        return "Good port: " + serverPort;
    }
}