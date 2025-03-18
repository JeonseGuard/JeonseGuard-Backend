package jeonseguard.backend.auth.infrastructure.provider;

import jeonseguard.backend.auth.infrastructure.provider.client.KakaoOauthClient;
import jeonseguard.backend.auth.presentation.dto.response.*;
import jeonseguard.backend.global.exception.error.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.*;
import org.springframework.web.reactive.function.BodyInserters;

@Component
@RequiredArgsConstructor
public class KakaoOauthProvider {
    private final KakaoOauthClient kakaoOauthClient;

    @Value("${security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    @Value("${security.oauth2.client.provider.kakao.token-uri}")
    private String tokenUri;

    @Value("${security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoUri;

    public KakaoTokenResponse getKakaoTokens(String code) {
        validateKakaoAuthorizationCode(code);
        return kakaoOauthClient.getKakaoTokens(tokenUri, createFormData(code));
    }

    public KakaoUserInfoResponse getKakaoUserInfo(String accessToken) {
        validateKakaoToken(accessToken);
        return kakaoOauthClient.getKakaoUserInfo(userInfoUri, accessToken);
    }

    private BodyInserters.FormInserter<String> createFormData(String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("redirect_uri", redirectUri);
        formData.add("code", code);
        return BodyInserters.fromFormData(formData);
    }

    private void validateKakaoAuthorizationCode(String code) {
        if (code == null || code.isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_KAKAO_AUTHORIZATION_CODE);
        }
    }

    private void validateKakaoToken(String accessToken) {
        if (accessToken == null || accessToken.isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_KAKAO_TOKEN);
        }
    }
}
