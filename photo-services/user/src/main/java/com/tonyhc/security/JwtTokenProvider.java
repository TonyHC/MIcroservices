package com.tonyhc.security;

import com.tonyhc.dto.UserDTO;
import com.tonyhc.service.CustomUserDetailsService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    @Value("${token.secret}")
    private String tokenSecret;

    @Value("${expiration.time}")
    private String expirationTime;

   private final CustomUserDetailsService customUserDetailsService;

    public JwtTokenProvider(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    public String generateToken(Authentication authentication) {
        String username = ((User) authentication.getPrincipal()).getUsername();
        UserDTO userDetails = customUserDetailsService.getUserDetailsByEmail(username);

        Date currentDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(currentDate.getTime() + Long.parseLong(expirationTime));

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userDetails.getUserId());

        return Jwts.builder()
                .setSubject(userDetails.getUserId())
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException signatureException) {
            System.out.println("Invalid JWT Signature");
        } catch (MalformedJwtException malformedJwtException) {
            System.out.println("Invalid JWT Token");
        } catch (ExpiredJwtException expiredJwtException) {
            System.out.println("Expired JWT Token");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            System.out.println("Unsupported JWT Token");
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("JWT claims string is empty");
        }

        return false;
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        return (String) claims.get("id");
    }
}