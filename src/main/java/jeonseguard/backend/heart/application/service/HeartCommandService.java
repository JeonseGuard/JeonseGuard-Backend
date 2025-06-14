package jeonseguard.backend.heart.application.service;

import jeonseguard.backend.heart.domain.entity.Heart;
import jeonseguard.backend.heart.domain.factory.HeartFactory;
import jeonseguard.backend.heart.domain.repository.HeartRepository;
import jeonseguard.backend.heart.presentation.dto.request.ToggleHeartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static jeonseguard.backend.global.constant.CacheKey.POST_ID_PREFIX;

@Transactional
@Service
@RequiredArgsConstructor
public class HeartCommandService {
    private final HeartRepository heartRepository;

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "post", key = "'" + POST_ID_PREFIX + "' + #request.postId()")
    })
    public void toggleHeart(Long userId, ToggleHeartRequest request) {
        if (heartRepository.existsByUserIdAndPostId(userId, request.postId())) {
            heartRepository.deleteByUserIdAndPostId(userId, request.postId());
        } else {
            Heart heart = HeartFactory.from(userId, request);
            heartRepository.save(heart);
        }
    }
}
