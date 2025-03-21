package jeonseguard.backend.auth.domain.service;

import jeonseguard.backend.auth.domain.repository.*;
import jeonseguard.backend.auth.infrastructure.provider.*;
import jeonseguard.backend.auth.presentation.dto.request.*;
import jeonseguard.backend.auth.presentation.dto.response.*;
import jeonseguard.backend.global.exception.error.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final KakaoOauthProvider oauthProvider;
    private final JwtTokenProvider tokenProvider;
    private final LogoutTokenStore logoutTokenStore;
    private final RefreshTokenStore refreshTokenStore;

    public KakaoUserInfoResponse getKakaoUserInfo(LoginRequest request) {
        KakaoTokenResponse response = oauthProvider.getKakaoTokens(request.code());
        return oauthProvider.getKakaoUserInfo(response.accessToken());
    }

    public TokenResponse getTokens(Long userId) {
        String accessToken = tokenProvider.generateAccessToken(userId);
        String refreshToken = tokenProvider.generateRefreshToken(userId);
        long accessTokenExpirationTime = tokenProvider.getAccessTokenExpirationTime();
        refreshTokenStore.saveRefreshToken(userId, refreshToken, accessTokenExpirationTime);
        return TokenResponse.of(accessToken, refreshToken);
    }

    public TokenResponse refreshTokens(RefreshRequest request) {
        String refreshToken = request.refreshToken();
        Long userId = tokenProvider.getUserIdFromToken(refreshToken);
        String storedRefreshToken = refreshTokenStore.getRefreshToken(userId);
        long refreshTokenExpirationTime = tokenProvider.getRefreshTokenExpirationTime();
        validateRefreshToken(refreshToken, storedRefreshToken);
        TokenResponse response = getTokens(userId);
        refreshTokenStore.removeRefreshToken(userId);
        refreshTokenStore.saveRefreshToken(userId, response.refreshToken(), refreshTokenExpirationTime);
        return response;
    }

    public void blacklistToken(LogoutRequest request) {
        String accessToken = request.accessToken();
        Long userId = tokenProvider.getUserIdFromToken(accessToken);
        long accessTokenExpirationTime = tokenProvider.getAccessTokenExpirationTime();
        logoutTokenStore.blacklistToken(accessToken, accessTokenExpirationTime);
        refreshTokenStore.removeRefreshToken(userId);
    }

    private void validateRefreshToken(String refreshToken, String storedRefreshToken) {
        if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
            throw new BusinessException(ErrorCode.INVALID_REFRESH_TOKEN);
        }
    }
}
