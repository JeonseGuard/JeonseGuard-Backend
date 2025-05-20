package jeonseguard.backend.heart.application.facade;

import jeonseguard.backend.heart.presentation.dto.request.ToggleHeartRequest;
import jeonseguard.backend.heart.presentation.dto.response.HeartResponse;
import jeonseguard.backend.heart.application.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HeartFacade {
    private final HeartService heartService;

    public HeartResponse togglePostHeart(Long userId, ToggleHeartRequest request) {
        heartService.toggleHeart(userId, request);
        long heartCount = heartService.countHeart(request);
        boolean heartStatus = heartService.hasHeart(userId, request);
        return HeartResponse.of(heartCount, heartStatus);
    }
}
