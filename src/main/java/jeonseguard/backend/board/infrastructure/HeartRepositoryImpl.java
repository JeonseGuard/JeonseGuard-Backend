package jeonseguard.backend.board.infrastructure;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.repository.HeartRepository;
import jeonseguard.backend.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HeartRepositoryImpl implements HeartRepository {
    private final HeartJpaRepository jpaRepository;

    @Override
    public Heart save(Heart heart) {
        return jpaRepository.save(heart);
    }

    @Override
    public boolean existsByTargetIdAndTargetAndUser(Long targetId, HeartTarget target, User user) {
        return jpaRepository.existsByTargetIdAndTargetAndUser(targetId, target, user);
    }

    @Override
    public void deleteByTargetIdAndTargetAndUser(Long targetId, HeartTarget target, User user) {
        jpaRepository.deleteByTargetIdAndTargetAndUser(targetId, target, user);
    }

    @Override
    public long countByTargetIdAndTarget(Long targetId, HeartTarget target) {
        return jpaRepository.countByTargetIdAndTarget(targetId, target);
    }
}
