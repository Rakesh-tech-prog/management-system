package com.management.utility;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

    private static final long EXPIRATION_MS = 24 * 60 * 60 * 1000; // 24 hours  
    // DONT KEEP IT TOO LONG FOR PRODUCTION ALWAY USE REFRESH TOKEN WHENEVER POSSIBLE

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(generateSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    
    
    private String generateSecretKey() {
		// Generate a random secret key for HS256
		Key scrKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		return Encoders.BASE64.encode(scrKey.getEncoded());
	}
    
    /**
     * Generate JWT token for a specific username
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validate token against a specific username and check expiration
     */
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     * Extract username from the token subject
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract expiration date
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * General method to extract any claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    // extract user role from token
    public String extractUserRole(String token) {
		return extractClaim(token, claims -> claims.get("role", String.class));
	}
}