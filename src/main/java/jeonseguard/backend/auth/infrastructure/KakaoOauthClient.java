package jeonseguard.backend.auth.infrastructure;

import jeonseguard.backend.auth.presentation.dto.request.KakaoTokenRequest;
import jeonseguard.backend.auth.presentation.dto.response.*;
import jeonseguard.backend.global.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class KakaoOauthClient {
    private final WebClient webClient;

    public KakaoTokenResponse getKakaoTokens(String tokenUri, KakaoTokenRequest request) {
        return webClient.post()
                .uri(tokenUri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(KakaoTokenResponse.class)
                .blockOptional()
                .orElseThrow(() -> new BusinessException(ErrorCode.KAKAO_TOKEN_FETCH_FAILED));
    }

    public KakaoUserInfoResponse getKakaoUserInfo(String userInfoUri, String accessToken) {
        return webClient.get()
                .uri(userInfoUri)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(KakaoUserInfoResponse.class)
                .blockOptional()
                .orElseThrow(() -> new BusinessException(ErrorCode.KAKAO_USER_INFO_FETCH_FAILED));
    }
}
