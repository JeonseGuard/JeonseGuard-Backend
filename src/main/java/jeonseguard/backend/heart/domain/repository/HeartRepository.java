package jeonseguard.backend.heart.domain.repository;

import jeonseguard.backend.heart.domain.entity.Heart;

public interface HeartRepository {
    Heart save(Heart heart);
    boolean existsByUserIdAndPostId(Long userId, Long postId);
    void deleteByUserIdAndPostId(Long userId, Long postId);
}
