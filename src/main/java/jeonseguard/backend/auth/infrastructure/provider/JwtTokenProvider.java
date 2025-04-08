package jeonseguard.backend.auth.infrastructure.provider;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jeonseguard.backend.global.config.properties.JwtTokenProperties;
import jeonseguard.backend.global.exception.error.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtTokenProperties jwtTokenProperties;

    public String generateAccessToken(Long userId) {
        return generateToken(userId, jwtTokenProperties.accessExpirationTime());
    }

    public String generateRefreshToken(Long userId) {
        return generateToken(userId, jwtTokenProperties.refreshExpirationTime());
    }

    public Long getUserIdFromToken(String token) {
        validateToken(token);
        return Long.parseLong(decodeToken(token).getSubject());
    }

    public long getAccessTokenExpirationTime() {
        return jwtTokenProperties.accessExpirationTime();
    }

    public long getRefreshTokenExpirationTime() {
        return jwtTokenProperties.refreshExpirationTime();
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
        return Keys.hmacShaKeyFor(jwtTokenProperties.secretKey().getBytes());
    }

    private void validateToken(String token) {
        try {
            decodeToken(token);
        } catch (JwtException | IllegalArgumentException ex) {
            throw new BusinessException(ErrorCode.INVALID_JWT_TOKEN);
        }
    }
}
