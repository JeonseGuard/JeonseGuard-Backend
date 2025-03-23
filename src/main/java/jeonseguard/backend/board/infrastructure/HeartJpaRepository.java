package jeonseguard.backend.board.infrastructure;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartJpaRepository extends JpaRepository<Heart, Long> {
    long countByTargetIdAndTarget(Long targetId, HeartTarget target);
    boolean existsByTargetIdAndTargetAndUser(Long targetId, HeartTarget target, User user);
    void deleteByTargetIdAndTargetAndUser(Long targetId, HeartTarget target, User user);
}
