package jeonseguard.backend.post.domain.repository;

import jeonseguard.backend.post.infrastructure.dto.PostDetailResponse;

import java.util.Optional;

public interface PostQueryRepository {
    Optional<PostDetailResponse> findDetailByUserIdAndId(Long userId, Long postId);
}
