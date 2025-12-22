// package com.example.demo.util;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;
// import java.security.Key;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;

// @Component
// public class JwtUtil {
//     private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
//     private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours
    
//     private Key getSigningKey() {
//         byte[] keyBytes = SECRET_KEY.getBytes();
//         return Keys.hmacShaKeyFor(keyBytes);
//     }
    
//     public String generateToken(Long userId, String email, String role) {
//         Map<String, Object> claims = new HashMap<>();
//         claims.put("userId", userId);
//         claims.put("role", role);
        
//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setSubject(email)
//                 .setIssuedAt(new Date(System.currentTimeMillis()))
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                 .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                 .compact();
//     }
    
//     public String extractUsername(String token) {
//         return extractClaim(token, Claims::getSubject);
//     }
    
//     public Long extractUserId(String token) {
//         return extractClaim(token, claims -> claims.get("userId", Long.class));
//     }
    
//     public String extractRole(String token) {
//         return extractClaim(token, claims -> claims.get("role", String.class));
//     }
    
//     public Date extractExpiration(String token) {
//         return extractClaim(token, Claims::getExpiration);
//     }
    
//     public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//         final Claims claims = extractAllClaims(token);
//         return claimsResolver.apply(claims);
//     }
    
//     private Claims extractAllClaims(String token) {
//         try {
//             return Jwts.parserBuilder()
//                     .setSigningKey(getSigningKey())
//                     .build()
//                     .parseClaimsJws(token)
//                     .getBody();
//         } catch (Exception e) {
//             throw new IllegalArgumentException("Invalid JWT token");
//         }
//     }
    
//     // FIXED: Changed to accept only token parameter
//     public Boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder()
//                 .setSigningKey(getSigningKey())
//                 .build()
//                 .parseClaimsJws(token);
//             return true;
//         } catch (JwtException | IllegalArgumentException e) {
//             return false;
//         }
//     }
    
//     // Optional: Keep the original validateToken with UserDetails if needed
//     public Boolean validateToken(String token, String email) {
//         final String username = extractUsername(token);
//         return (username.equals(email) && !isTokenExpired(token));
//     }
    
//     private Boolean isTokenExpired(String token) {
//         return extractExpiration(token).before(new Date());
//     }
// }