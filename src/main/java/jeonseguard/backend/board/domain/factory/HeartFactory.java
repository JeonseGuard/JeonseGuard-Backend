package jeonseguard.backend.board.domain.factory;

import jeonseguard.backend.board.domain.entity.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeartFactory {
    public static Heart createHeart(Long userId, Long targetId, HeartTarget target) {
        return Heart.builder()
                .targetId(targetId)
                .target(target)
                .userId(userId)
                .build();
    }
}
