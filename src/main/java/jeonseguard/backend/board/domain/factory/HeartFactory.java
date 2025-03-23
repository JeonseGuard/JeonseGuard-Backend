package jeonseguard.backend.board.domain.factory;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.user.domain.entity.User;

public class HeartFactory {
    public static Heart createHeart(Long targetId, HeartTarget target, User user) {
        return Heart.builder()
                .targetId(targetId)
                .target(target)
                .user(user)
                .build();
    }
}
