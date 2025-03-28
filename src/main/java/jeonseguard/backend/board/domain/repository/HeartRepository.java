package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.*;

public interface HeartRepository {
    Heart save(Heart heart);
    boolean existsByUserIdAndTargetIdAndTarget(Long userId, Long targetId, HeartTarget target);
    void deleteByUserIdAndTargetIdAndTarget(Long userId, Long targetId, HeartTarget target);
}
