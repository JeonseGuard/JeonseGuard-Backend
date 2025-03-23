package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.user.domain.entity.User;

public interface HeartRepository {
    Heart save(Heart heart);
    boolean existsByTargetIdAndTargetAndUser(Long targetId, HeartTarget target, User user);
    void deleteByTargetIdAndTargetAndUser(Long targetId, HeartTarget target, User user);
    long countByTargetIdAndTarget(Long targetId, HeartTarget target);
}
