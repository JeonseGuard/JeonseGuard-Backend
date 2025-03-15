package jeonseguard.backend.auth.domain;

import jeonseguard.backend.auth.infrastructure.*;
import jeonseguard.backend.auth.presentation.dto.request.TokenRequest;
import jeonseguard.backend.auth.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final KakaoOauthProvider oauthProvider;
    private final JwtTokenProvider tokenProvider;

    public KakaoUserInfoResponse getKakaoUserInfo(TokenRequest request) {
        KakaoTokenResponse response = oauthProvider.getKakaoTokens(request.code());
        return oauthProvider.getKakaoUserInfo(response.accessToken());
    }

    public TokenResponse getTokens(Long userId) {
        return tokenProvider.getTokens(userId);
    }
}
