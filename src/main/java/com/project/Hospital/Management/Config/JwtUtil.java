package com.project.Hospital.Management.Config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static final String SECRET_KEY_STRING =
            "akshatrawat27102004#pdr#ssr#ar#nihon";

    private final Key SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim(
                        "roles",
                        userDetails.getAuthorities()
                                .stream()
                                .map(Object::toString)
                                .collect(Collectors.toList())
                )
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }


    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername())
                    && !isTokenExpired(token);
        } catch (ExpiredJwtException e) {
            System.out.println("Token Expired: " + e.getMessage());
            return false;
        } catch (SignatureException e) {
            System.out.println("Invalid Signature: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Invalid Token: " + e.getMessage());
            return false;
        }
    }
}
