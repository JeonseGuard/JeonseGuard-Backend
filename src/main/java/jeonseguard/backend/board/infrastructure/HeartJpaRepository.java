package jeonseguard.backend.board.infrastructure;

import jeonseguard.backend.board.domain.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartJpaRepository extends JpaRepository<Heart, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
    void deleteByUserIdAndPostId(Long userId, Long postId);
}
