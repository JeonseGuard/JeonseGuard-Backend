package jeonseguard.backend.auth.infrastructure;

import jeonseguard.backend.auth.presentation.dto.request.KakaoTokenRequest;
import jeonseguard.backend.auth.presentation.dto.response.*;
import jeonseguard.backend.global.exception.BusinessException;
import jeonseguard.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
        KakaoTokenRequest request = KakaoTokenRequest.of(clientId, clientSecret, redirectUri, code);
        return kakaoOauthClient.getKakaoTokens(tokenUri, request);
    }

    public KakaoUserInfoResponse getKakaoUserInfo(String accessToken) {
        validateKakaoToken(accessToken);
        return kakaoOauthClient.getKakaoUserInfo(userInfoUri, accessToken);
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
