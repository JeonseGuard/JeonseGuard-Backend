package jeonseguard.backend.heart.application.facade;

import jeonseguard.backend.heart.application.service.*;
import jeonseguard.backend.heart.presentation.dto.request.ToggleHeartRequest;
import jeonseguard.backend.heart.presentation.dto.response.HeartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HeartFacade {
    private final HeartReadService heartReadService;
    private final HeartWriteService heartWriteService;

    public HeartResponse togglePostHeart(Long userId, ToggleHeartRequest request) {
        heartWriteService.toggleHeart(userId, request);
        long heartCount = heartReadService.countHeart(request);
        boolean heartStatus = heartReadService.hasHeart(userId, request);
        return HeartResponse.of(heartCount, heartStatus);
    }
}
