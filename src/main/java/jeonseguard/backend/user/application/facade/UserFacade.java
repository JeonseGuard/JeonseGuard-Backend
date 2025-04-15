package jeonseguard.backend.user.application.facade;

import jeonseguard.backend.user.application.service.UserService;
import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.presentation.dto.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;

    public UserInfoResponse getUser(Long userId) {
        User user = userService.getUser(userId);
        return UserInfoResponse.fromEntity(user);
    }
}
