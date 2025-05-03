package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.*;

public interface HeartRepository {
    Heart save(Heart heart);
    boolean existsByUserIdAndPostId(Long userId, Long postId);
    void deleteByUserIdAndPostId(Long userId, Long postId);
}
