package jeonseguard.backend.post.domain.repository;

import jeonseguard.backend.post.domain.entity.PostCategory;
import jeonseguard.backend.post.infrastructure.dto.PostDetailResponse;
import jeonseguard.backend.post.infrastructure.dto.PostResponse;
import org.springframework.data.domain.*;

import java.util.Optional;

public interface PostQueryRepository {
    Page<PostResponse> findAllWithCounts(PostCategory category, Pageable pageable);
    Optional<PostDetailResponse> findDetailByUserIdAndId(Long userId, Long postId);
}
