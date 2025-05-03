package jeonseguard.backend.board.domain.repository;

public interface HeartQueryRepository {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
    long countByPostId(Long postId);
}
