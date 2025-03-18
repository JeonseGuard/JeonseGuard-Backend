package jeonseguard.backend.user.application;

import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.domain.service.UserService;
import jeonseguard.backend.user.presentation.dto.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;

    public UserInfoResponse getUserInfo(Long userId) {
        User user = userService.getUserOrThrow(userId);
        return UserInfoResponse.fromEntity(user);
    }
}
