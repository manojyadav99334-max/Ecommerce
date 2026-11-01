package com.ecommerce.apigateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    private SecretKey key;

    @PostConstruct
    public void init()
    {
        this.key=Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims extractClaim(String token)
    {
        return Jwts.parser()
                .verifyWith(key)
                .build().parseSignedClaims(token)
                .getPayload();
    }
    public boolean validateToken(String token)
    {
        try
        {
            extractClaim(token);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public String getUserId(String token)
    {
        return extractClaim(token).getSubject();
    }
    public String getRole(String token)
    {
        return extractClaim(token).get("role",String.class);
    }
}
