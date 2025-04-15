package jeonseguard.backend.user.domain.policy;

import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.user.domain.entity.User;

public class UserPolicy {
    public static void validateAdmin(User user, ErrorCode errorCode) {
        if (!user.isAdmin()) {
            throw new BusinessException(errorCode);
        }
    }
}
