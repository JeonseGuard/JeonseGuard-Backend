package jeonseguard.backend.heart.application.service;

import jeonseguard.backend.heart.domain.entity.Heart;
import jeonseguard.backend.heart.domain.factory.HeartFactory;
import jeonseguard.backend.heart.domain.repository.*;
import jeonseguard.backend.heart.presentation.dto.request.ToggleHeartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final HeartQueryRepository heartQueryRepository;

    @Transactional(readOnly = true)
    public boolean hasHeart(Long userId, ToggleHeartRequest request) {
        return heartQueryRepository.existsByUserIdAndPostId(userId, request.postId());
    }

    @Transactional(readOnly = true)
    public long countHeart(ToggleHeartRequest request) {
        return heartQueryRepository.countByPostId(request.postId());
    }

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "postDetail", key = "'post::id:' + #request.postId()")
    })
    @Transactional
    public void toggleHeart(Long userId, ToggleHeartRequest request) {
        if (heartRepository.existsByUserIdAndPostId(userId, request.postId())) {
            heartRepository.deleteByUserIdAndPostId(userId, request.postId());
        } else {
            Heart heart = HeartFactory.from(userId, request);
            heartRepository.save(heart);
        }
    }
}
