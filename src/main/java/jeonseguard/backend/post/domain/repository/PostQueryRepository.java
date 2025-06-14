package jeonseguard.backend.post.domain.repository;

import jeonseguard.backend.post.infrastructure.dto.PostSummary;

import java.util.Optional;

public interface PostQueryRepository {
    Optional<PostSummary> findByUserIdAndId(Long userId, Long postId);
}
