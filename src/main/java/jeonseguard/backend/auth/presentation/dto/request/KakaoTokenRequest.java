package jeonseguard.backend.auth.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record KakaoTokenRequest(
        @Schema(description = "OAuth 2.0 인증 방식") String grantType,
        @Schema(description = "카카오 클라이언트 ID") String clientId,
        @Schema(description = "카카오 클라이언트 시크릿") String clientSecret,
        @Schema(description = "카카오 리다이렉트 URI") String redirectUri,
        @Schema(description = "카카오 인가 코드") String code
) {
    public static KakaoTokenRequest of(String clientId, String clientSecret, String redirectUri, String code) {
        return new KakaoTokenRequest(
                "authorization_code",
                clientId,
                clientSecret,
                redirectUri,
                code
        );
    }
}
