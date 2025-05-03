package jeonseguard.backend.board.domain.factory;

import jeonseguard.backend.board.domain.entity.Heart;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeartFactory {
    public static Heart from(Long userId, Long postId) {
        return Heart.builder()
                .postId(postId)
                .userId(userId)
                .build();
    }
}
