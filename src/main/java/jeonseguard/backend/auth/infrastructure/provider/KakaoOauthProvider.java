package jeonseguard.backend.auth.infrastructure.provider;

import jeonseguard.backend.auth.infrastructure.client.KakaoOauthClient;
import jeonseguard.backend.auth.infrastructure.dto.*;
import jeonseguard.backend.global.config.properties.KakaoOauthProperties;
import jeonseguard.backend.global.exception.error.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.*;
import org.springframework.web.reactive.function.BodyInserters;

@Component
@RequiredArgsConstructor
public class KakaoOauthProvider {
    private final KakaoOauthClient kakaoOauthClient;
    private final KakaoOauthProperties kakaoOauthProperties;

    public KakaoTokenResponse getKakaoTokens(String code) {
        validateKakaoAuthorizationCode(code);
        return kakaoOauthClient.getKakaoTokens(kakaoOauthProperties.tokenUri(), createFormData(code));
    }

    public KakaoUserInfoResponse getKakaoUserInfo(String accessToken) {
        validateKakaoToken(accessToken);
        return kakaoOauthClient.getKakaoUserInfo(kakaoOauthProperties.userInfoUri(), accessToken);
    }

    private BodyInserters.FormInserter<String> createFormData(String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", kakaoOauthProperties.clientId());
        formData.add("client_secret", kakaoOauthProperties.clientSecret());
        formData.add("redirect_uri", kakaoOauthProperties.redirectUri());
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
