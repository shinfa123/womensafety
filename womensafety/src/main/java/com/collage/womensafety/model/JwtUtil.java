package com.collage.womensafety.model;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

    // Use a Base64-encoded secret key
    private static final String SECRET = "your-256-bit-secret-key-here-should-be-base64-encoded";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    /**
     * Extracts the username (subject) from the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from the token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from the token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser() // Use 'parserBuilder' for newer versions of jjwt
                .setSigningKey(SECRET_KEY) // Set the signing key
                .build() // Build the JwtParser
                .parseClaimsJws(token) // 'parseClaimsJws' is appropriate for JWS tokens
                .getBody(); // Extract the claims body
    }
    
    /**
     * Checks if the token is expired.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Generates a new token for the given user.
     */
    public String generateToken(UserDetails userDetails) {
        return createToken(userDetails.getUsername());
    }

    /**
     * Creates a token with the subject.
     */
    private String createToken(String subject) {
        return Jwts.builder()
                .subject(subject) // Using the recommended method 'subject()' instead of 'setSubject()'
                .issuedAt(new Date(System.currentTimeMillis())) // Using the newer 'issuedAt()' method
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Using 'expiration()' method for expiration
                .claim("custom_claim", "custom_value") // Add individual claims using claim() method
                .signWith(SECRET_KEY) // Use the SecretKey object
                .compact();
    }

    /**
     * Validates the token by checking username and expiration date.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
