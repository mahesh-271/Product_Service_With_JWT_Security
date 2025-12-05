package com.product.management.product_management.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private  final String SECRET ="mysecretkey123mysecretkey123mysecretkey123";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String userName , List<String> roles){

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  //1 hour
                .claim("roles", roles)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token){

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public List<String> extractRoles(String token) {
           List<?> rolesObj =  extractAllClaims(token).get("roles", List.class);
           if(rolesObj != null){

               return rolesObj.stream()
                       .map(Object::toString)
                       .toList();
           }
           return List.of();
        }
}
