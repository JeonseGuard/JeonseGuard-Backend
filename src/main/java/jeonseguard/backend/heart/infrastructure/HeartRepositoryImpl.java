package jeonseguard.backend.heart.infrastructure;

import jeonseguard.backend.heart.domain.entity.Heart;
import jeonseguard.backend.heart.domain.repository.HeartRepository;
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
