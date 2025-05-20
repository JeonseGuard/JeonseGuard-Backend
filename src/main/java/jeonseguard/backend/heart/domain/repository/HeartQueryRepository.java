package jeonseguard.backend.heart.domain.repository;

public interface HeartQueryRepository {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
    long countByPostId(Long postId);
}
