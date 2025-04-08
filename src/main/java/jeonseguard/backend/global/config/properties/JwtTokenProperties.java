package jeonseguard.backend.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.jwt")
public record JwtTokenProperties(
        String secretKey,
        long accessExpirationTime,
        long refreshExpirationTime
) {
}
