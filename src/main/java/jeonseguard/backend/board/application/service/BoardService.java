package jeonseguard.backend.board.application.service;

import jeonseguard.backend.board.domain.BoardQueryRepository;
import jeonseguard.backend.board.presentation.dto.BoardResponse;
import jeonseguard.backend.post.domain.entity.PostCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardQueryRepository boardQueryRepository;

    @Cacheable(value = "board", key = "'board::category:' + #category + ':page:' + #pageable.pageNumber")
    @Transactional(readOnly = true)
    public BoardResponse getBoardByCategory(PostCategory category, Pageable pageable) {
        return boardQueryRepository.findAllWithCounts(category, pageable);
    }
}
