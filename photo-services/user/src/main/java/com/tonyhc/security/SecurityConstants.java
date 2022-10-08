package com.tonyhc.security;

public class SecurityConstants {
    public static final String H2_URL = "h2-console/**";
    public static final String API_GATEWAY_URL = "10.0.0.2";
    public static final String LOGIN_URL = "/api/v1/users/login";
    public static final String TOKEN_SECRET = "SecretKeyToGenerateJWTs";
    public static final long EXPIRATION_TIME = 3600000; // 1 hour in milliseconds
}
