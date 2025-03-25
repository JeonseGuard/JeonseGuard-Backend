package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.presentation.dto.response.*;
import org.springframework.data.domain.*;

import java.util.Optional;

public interface PostQueryRepository {
    Page<PostResponse> findAllWithCounts(BoardCategory category, Pageable pageable);
    Optional<PostDetailResponse> findDetailById(Long userId, Long postId, String category);
    Optional<Post> findByUserIdAndIdAndCategory(Long userId, Long postId, BoardCategory category);
}
