package jeonseguard.backend.auth.domain;

import jeonseguard.backend.auth.infrastructure.*;
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
        tokenProvider.validateToken(refreshToken);
        Long userId = tokenProvider.getUserIdFromToken(refreshToken);
        String storedRefreshToken = refreshTokenRepository.getRefreshToken(userId);
        validateRefreshToken(refreshToken, storedRefreshToken);
        TokenResponse newTokens = getTokens(userId);
        updateRefreshToken(userId, newTokens.refreshToken());
        return newTokens;
    }

    private void updateRefreshToken(Long userId, String refreshToken) {
        refreshTokenRepository.removeRefreshToken(userId);
        refreshTokenRepository.saveRefreshToken(userId, refreshToken);
    }

    private void validateRefreshToken(String refreshToken, String storedRefreshToken) {
        if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
            throw new BusinessException(ErrorCode.INVALID_REFRESH_TOKEN);
        }
    }
}
