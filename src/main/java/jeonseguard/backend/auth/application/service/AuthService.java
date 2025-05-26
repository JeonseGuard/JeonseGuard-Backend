package jeonseguard.backend.auth.application.service;

import jeonseguard.backend.auth.domain.*;
import jeonseguard.backend.auth.infrastructure.dto.*;
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

    public RefreshTokenResponse getCachedRefreshTokenInfo(RefreshRequest request) {
        String refreshToken = request.refreshToken();
        Long userId = tokenProvider.getUserIdFromToken(refreshToken);
        String cachedRefreshToken = refreshTokenStore.getRefreshToken(userId);
        return RefreshTokenResponse.of(userId, refreshToken, cachedRefreshToken);
    }

    public void cacheRefreshToken(Long userId, TokenResponse response) {
        long refreshTokenExpirationTime = tokenProvider.getRefreshTokenExpirationTime();
        refreshTokenStore.removeRefreshToken(userId);
        refreshTokenStore.saveRefreshToken(userId, response.refreshToken(), refreshTokenExpirationTime);
    }

    public void blacklistToken(LogoutRequest request) {
        String accessToken = request.accessToken();
        Long userId = tokenProvider.getUserIdFromToken(accessToken);
        long accessTokenExpirationTime = tokenProvider.getAccessTokenExpirationTime();
        logoutTokenStore.blacklistToken(accessToken, accessTokenExpirationTime);
        refreshTokenStore.removeRefreshToken(userId);
    }

    public void validateRefreshToken(RefreshTokenResponse response) {
        if (response.cachedRefreshToken() == null || !response.cachedRefreshToken().equals(response.refreshToken())) {
            throw new BusinessException(ErrorCode.INVALID_REFRESH_TOKEN);
        }
    }
}
