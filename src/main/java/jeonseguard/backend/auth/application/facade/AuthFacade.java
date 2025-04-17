package jeonseguard.backend.auth.application.facade;

import jeonseguard.backend.auth.application.service.AuthService;
import jeonseguard.backend.auth.infrastructure.dto.KakaoUserInfoResponse;
import jeonseguard.backend.auth.presentation.dto.request.*;
import jeonseguard.backend.auth.presentation.dto.response.*;
import jeonseguard.backend.user.application.service.UserService;
import jeonseguard.backend.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {
    private final AuthService authService;
    private final UserService userService;

    public TokenResponse login(LoginRequest request) {
        KakaoUserInfoResponse kakaoUserInfo = authService.getKakaoUserInfo(request);
        User user = userService.getOrCreateUser(kakaoUserInfo);
        return authService.getTokens(user.getId());
    }

    public TokenResponse refresh(RefreshRequest request) {
        return authService.refreshTokens(request);
    }

    public void logout(LogoutRequest request) {
        authService.blacklistToken(request);
    }
}
