package jeonseguard.backend.auth.infrastructure;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jeonseguard.backend.global.exception.error.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.access-expiration-time}")
    private long accessExpirationTime;

    @Value("${security.jwt.refresh-expiration-time}")
    private long refreshExpirationTime;

    public String generateAccessToken(Long userId) {
        return generateToken(userId, accessExpirationTime);
    }

    public String generateRefreshToken(Long userId) {
        return generateToken(userId, refreshExpirationTime);
    }

    public Long getUserIdFromToken(String token) {
        return Long.parseLong(decodeToken(token).getSubject());
    }

    public void validateToken(String token) {
        try {
            decodeToken(token);
        } catch (JwtException | IllegalArgumentException ex) {
            throw new BusinessException(ErrorCode.INVALID_JWT_TOKEN);
        }
    }

    private String generateToken(Long userId, long expirationTime) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSecretKey())
                .compact();
    }

    private Claims decodeToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
