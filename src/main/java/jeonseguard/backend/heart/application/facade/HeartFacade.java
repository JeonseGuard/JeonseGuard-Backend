package jeonseguard.backend.heart.application.facade;

import jeonseguard.backend.heart.application.service.*;
import jeonseguard.backend.heart.presentation.dto.request.ToggleHeartRequest;
import jeonseguard.backend.heart.presentation.dto.response.HeartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HeartFacade {
    private final HeartQueryService heartQueryService;
    private final HeartCommandService heartCommandService;

    public HeartResponse togglePostHeart(Long userId, ToggleHeartRequest request) {
        heartCommandService.toggleHeart(userId, request);
        long heartCount = heartQueryService.countHeart(request);
        boolean heartStatus = heartQueryService.hasHeart(userId, request);
        return HeartResponse.of(heartCount, heartStatus);
    }
}
