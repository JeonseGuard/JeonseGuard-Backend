package jeonseguard.backend.board.infrastructure;

import jeonseguard.backend.board.domain.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartJpaRepository extends JpaRepository<Heart, Long> {
    boolean existsByUserIdAndTargetIdAndTarget(Long userId, Long targetId, HeartTarget target);
    void deleteByUserIdAndTargetIdAndTarget(Long userId, Long targetId, HeartTarget target);
}
