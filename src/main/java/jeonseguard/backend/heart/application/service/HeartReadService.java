package jeonseguard.backend.heart.application.service;

import jeonseguard.backend.heart.domain.repository.HeartQueryRepository;
import jeonseguard.backend.heart.presentation.dto.request.ToggleHeartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class HeartReadService {
    private final HeartQueryRepository heartQueryRepository;

    public boolean hasHeart(Long userId, ToggleHeartRequest request) {
        return heartQueryRepository.existsByUserIdAndPostId(userId, request.postId());
    }

    public long countHeart(ToggleHeartRequest request) {
        return heartQueryRepository.countByPostId(request.postId());
    }
}
