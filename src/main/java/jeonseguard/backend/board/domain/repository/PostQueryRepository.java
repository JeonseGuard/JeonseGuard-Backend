package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.infrastructure.dto.*;
import org.springframework.data.domain.*;

import java.util.Optional;

public interface PostQueryRepository {
    Page<PostResponse> findAllWithCounts(BoardCategory category, Pageable pageable);
    Optional<PostDetailResponse> findDetailByUserIdAndId(Long userId, Long postId);
    Optional<Post> findByUserIdAndIdAndCategory(Long userId, Long postId, BoardCategory category);
}
