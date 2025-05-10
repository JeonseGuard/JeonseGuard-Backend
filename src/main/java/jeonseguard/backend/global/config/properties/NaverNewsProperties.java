package jeonseguard.backend.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "news.naver")
public record NaverNewsProperties(
        String clientId,
        String clientSecret,
        String endpoint,
        String query,
        String display
) {
}
