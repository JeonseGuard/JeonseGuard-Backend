package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.user.domain.entity.User;

public interface HeartRepository {
    Heart save(Heart heart);
    long countByTargetIdAndTarget(Long targetId, HeartTarget target);
    boolean existsByTargetIdAndTargetAndUser(Long targetId, HeartTarget target, User user);
    void deleteByTargetIdAndTargetAndUser(Long targetId, HeartTarget target, User user);
}
