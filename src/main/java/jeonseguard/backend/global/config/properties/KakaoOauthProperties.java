package jeonseguard.backend.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.oauth2.kakao")
public record KakaoOauthProperties(
        String clientId,
        String clientSecret,
        String redirectUri,
        String tokenUri,
        String userInfoUri
) {
}
