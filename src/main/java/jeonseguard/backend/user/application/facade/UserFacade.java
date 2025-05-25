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
    private final UserReadService userReadService;
    private final UserWriteService userWriteService;

    public UserInfoResponse getUserInfo(Long userId) {
        User user = userReadService.getUser(userId);
        String role = user.getRole().toString();
        return UserInfoResponse.from(user, role);
    }

    public void updateNickname(Long userId, UpdateNicknameRequest request) {
        User user = userReadService.getUser(userId);
        userWriteService.updateNickname(user, request);
    }

    public void updateProfileImage(Long userId, UpdateProfileImageRequest request) {
        User user = userReadService.getUser(userId);
        userWriteService.updateProfileImage(user, request);
    }
}
