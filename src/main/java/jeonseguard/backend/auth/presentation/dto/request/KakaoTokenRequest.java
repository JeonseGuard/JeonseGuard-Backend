package jeonseguard.backend.auth.presentation.dto.request;

public record KakaoTokenRequest(
        String grantType,
        String clientId,
        String clientSecret,
        String redirectUri,
        String code
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
