package jeonseguard.backend.board.infrastructure.repository;

import jeonseguard.backend.board.domain.entity.Heart;
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
    public boolean existsByUserIdAndPostId(Long userId, Long postId) {
        return jpaRepository.existsByUserIdAndPostId(userId, postId);
    }

    @Override
    public void deleteByUserIdAndPostId(Long userId, Long postId) {
        jpaRepository.deleteByUserIdAndPostId(userId, postId);
    }
}
