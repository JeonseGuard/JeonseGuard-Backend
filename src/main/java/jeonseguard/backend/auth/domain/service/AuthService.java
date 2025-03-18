package jeonseguard.backend.auth.domain.service;

import jeonseguard.backend.auth.infrastructure.provider.*;
import jeonseguard.backend.auth.infrastructure.repository.*;
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
    private final RefreshTokenRepository refreshTokenRepository;
    private final LogoutTokenRepository logoutTokenRepository;

    public KakaoUserInfoResponse getKakaoUserInfo(LoginRequest request) {
        KakaoTokenResponse response = oauthProvider.getKakaoTokens(request.code());
        return oauthProvider.getKakaoUserInfo(response.accessToken());
    }

    public TokenResponse getTokens(Long userId) {
        String accessToken = tokenProvider.generateAccessToken(userId);
        String refreshToken = tokenProvider.generateRefreshToken(userId);
        refreshTokenRepository.saveRefreshToken(userId, refreshToken);
        return TokenResponse.of(accessToken, refreshToken);
    }

    public TokenResponse refreshTokens(RefreshRequest request) {
        String refreshToken = request.refreshToken();
        Long userId = tokenProvider.getUserIdFromToken(refreshToken);
        String storedRefreshToken = refreshTokenRepository.getRefreshToken(userId);
        validateRefreshToken(refreshToken, storedRefreshToken);
        TokenResponse response = getTokens(userId);
        refreshTokenRepository.removeRefreshToken(userId);
        refreshTokenRepository.saveRefreshToken(userId, response.refreshToken());
        return response;
    }

    public void blacklistToken(LogoutRequest request) {
        String accessToken = request.accessToken();
        Long userId = tokenProvider.getUserIdFromToken(accessToken);
        logoutTokenRepository.blacklistToken(accessToken);
        refreshTokenRepository.removeRefreshToken(userId);
    }

    private void validateRefreshToken(String refreshToken, String storedRefreshToken) {
        if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
            throw new BusinessException(ErrorCode.INVALID_REFRESH_TOKEN);
        }
    }
}
