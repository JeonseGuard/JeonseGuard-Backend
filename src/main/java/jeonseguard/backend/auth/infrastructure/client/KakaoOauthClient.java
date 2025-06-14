package jeonseguard.backend.auth.infrastructure.client;

import jeonseguard.backend.auth.infrastructure.dto.*;
import jeonseguard.backend.global.exception.error.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class KakaoOauthClient {
    private final WebClient webClient;

    public KakaoTokenResponse getKakaoTokens(String tokenUri, BodyInserters.FormInserter<String> formData) {
        return webClient.post()
                .uri(tokenUri)
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .body(formData)
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
