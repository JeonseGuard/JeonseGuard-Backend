package jeonseguard.backend.board.domain;

import jeonseguard.backend.board.presentation.dto.BoardResponse;
import jeonseguard.backend.post.domain.entity.PostCategory;
import org.springframework.data.domain.Pageable;

public interface BoardQueryRepository {
    BoardResponse findAllWithCounts(PostCategory category, Pageable pageable);
}
