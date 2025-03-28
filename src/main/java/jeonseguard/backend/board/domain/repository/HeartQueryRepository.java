package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.HeartTarget;

public interface HeartQueryRepository {
    boolean existsByTarget(Long userId, Long targetId, HeartTarget target);
    long countByTarget(Long targetId, HeartTarget target);
}
