package jeonseguard.backend.auth.application.facade;

import jeonseguard.backend.auth.application.service.AuthService;
import jeonseguard.backend.auth.infrastructure.dto.*;
import jeonseguard.backend.auth.presentation.dto.request.*;
import jeonseguard.backend.auth.presentation.dto.response.*;
import jeonseguard.backend.user.application.service.*;
import jeonseguard.backend.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {
    private final AuthService authService;
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public TokenResponse login(LoginRequest request) {
        KakaoUserInfoResponse response = authService.getKakaoUserInfo(request);
        User user = getOrCreateUser(response);
        Long userId = user.getId();
        return authService.getTokens(userId);
    }

    public TokenResponse refresh(RefreshRequest request) {
        RefreshTokenResponse response = authService.getRefreshTokenWithUserIdAndCache(request);
        authService.validateRefreshToken(response);
        Long userId = response.userId();
        TokenResponse tokens = authService.getTokens(userId);
        authService.cacheRefreshToken(userId, tokens);
        return tokens;
    }

    public void logout(LogoutRequest request) {
        authService.blacklistToken(request);
    }

    private User getOrCreateUser(KakaoUserInfoResponse response) {
        return userQueryService.getUserByKakaoId(response.kakaoId())
                .orElseGet(() -> userCommandService.createUser(response));
    }
}
