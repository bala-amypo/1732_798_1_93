public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
}

// Add this helper method if missing:
public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
}