package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.HeartTarget;

import java.util.*;

public interface HeartQueryRepository {
    boolean existsByTarget(Long userId, Long targetId, HeartTarget target);
    long countByTarget(Long targetId, HeartTarget target);
    Set<Long> findHeartedTargetIds(Long userId, List<Long> targetIds, HeartTarget target);
}
