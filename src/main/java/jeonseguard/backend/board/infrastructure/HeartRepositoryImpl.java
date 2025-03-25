package jeonseguard.backend.board.infrastructure;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.repository.HeartRepository;
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
    public boolean existsByUserIdAndTargetIdAndTarget(Long userId, Long targetId, HeartTarget target) {
        return jpaRepository.existsByUserIdAndTargetIdAndTarget(targetId, targetId, target);
    }

    @Override
    public void deleteByUserIdAndTargetIdAndTarget(Long userId, Long targetId, HeartTarget target) {
        jpaRepository.deleteByUserIdAndTargetIdAndTarget(targetId, targetId, target);
    }
}
