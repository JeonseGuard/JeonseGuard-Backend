package jeonseguard.backend.board.domain.repository;

import jeonseguard.backend.board.domain.entity.BoardCategory;
import jeonseguard.backend.board.presentation.dto.response.*;
import org.springframework.data.domain.*;

public interface PostQueryRepository {
    Page<PostResponse> findAllWithCounts(BoardCategory category, Pageable pageable);
    PostDetailResponse findDetailById(Long userId, Long postId, String category);
}
