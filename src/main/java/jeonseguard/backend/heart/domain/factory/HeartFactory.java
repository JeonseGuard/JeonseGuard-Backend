package jeonseguard.backend.heart.domain.factory;

import jeonseguard.backend.heart.domain.entity.Heart;
import jeonseguard.backend.heart.presentation.dto.request.ToggleHeartRequest;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeartFactory {
    public static Heart from(Long userId, ToggleHeartRequest request) {
        return Heart.builder()
                .postId(request.postId())
                .userId(userId)
                .build();
    }
}
