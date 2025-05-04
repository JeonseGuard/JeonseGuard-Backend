package jeonseguard.backend.board.application.service;

import jeonseguard.backend.board.domain.entity.Heart;
import jeonseguard.backend.board.domain.factory.HeartFactory;
import jeonseguard.backend.board.domain.repository.*;
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
    public boolean hasHeart(Long userId, Long postId) {
        return heartQueryRepository.existsByUserIdAndPostId(userId, postId);
    }

    @Transactional(readOnly = true)
    public long countHeart(Long postId) {
        return heartQueryRepository.countByPostId(postId);
    }

    @Caching(evict = {
            @CacheEvict(value = "postPage", allEntries = true),
            @CacheEvict(value = "postDetail", key = "'post::id:' + #postId")
    })
    @Transactional
    public void toggleHeart(Long userId, Long postId) {
        if (heartRepository.existsByUserIdAndPostId(userId, postId)) {
            heartRepository.deleteByUserIdAndPostId(userId, postId);
        } else {
            Heart heart = HeartFactory.from(userId, postId);
            heartRepository.save(heart);
        }
    }
}
