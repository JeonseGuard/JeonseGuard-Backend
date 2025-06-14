package jeonseguard.backend.user.application.facade;

import jeonseguard.backend.user.application.service.*;
import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.presentation.dto.request.*;
import jeonseguard.backend.user.presentation.dto.respone.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UserInfoResponse getUserInfo(Long userId) {
        User user = userQueryService.getUser(userId);
        String role = user.getRole().toString();
        return UserInfoResponse.from(user, role);
    }

    public void updateNickname(Long userId, UpdateNicknameRequest request) {
        User user = userQueryService.getUser(userId);
        userCommandService.updateNickname(user, request);
    }

    public void updateProfileImage(Long userId, UpdateProfileImageRequest request) {
        User user = userQueryService.getUser(userId);
        userCommandService.updateProfileImage(user, request);
    }
}
