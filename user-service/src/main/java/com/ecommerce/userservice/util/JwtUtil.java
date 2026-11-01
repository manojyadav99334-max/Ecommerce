package com.ecommerce.userservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.sql.Date;

@Component
public class JwtUtil {
    private final SecretKey key;
    private final long jwtExpirationMs;

    public JwtUtil(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.expiration-ms}") long jwtExpirationMs)
    {
        this.jwtExpirationMs=jwtExpirationMs;
        this.key=Keys.hmacShaKeyFor(secret.getBytes());
    }
    public String generateToken(long userId,String email,String role)
    {
        long now=System.currentTimeMillis();
        return Jwts.builder().setSubject(String.valueOf(userId))
                .claim("email",email)
                .claim("role",role)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now+jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }
    public Jws<Claims> validateToken(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
    public Long getUserIdFromToken(String token)
    {
        Claims claims=validateToken(token)
                .getBody();
       return Long.valueOf(claims.getSubject());
    }
}
