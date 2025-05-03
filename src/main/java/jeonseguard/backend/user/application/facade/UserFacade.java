package jeonseguard.backend.user.application.facade;

import jeonseguard.backend.user.application.service.UserService;
import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.presentation.dto.request.*;
import jeonseguard.backend.user.presentation.dto.respone.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;

    public UserInfoResponse getUser(Long userId) {
        return userService.getUserInfo(userId);
    }

    public void updateNickname(Long userId, UpdateNicknameRequest request) {
        User user = userService.getUser(userId);
        userService.updateNickname(user, request);
    }

    public void updateProfileImage(Long userId, UpdateProfileImageRequest request) {
        User user = userService.getUser(userId);
        userService.updateProfileImage(user, request);
    }
}
