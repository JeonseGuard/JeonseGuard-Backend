package jeonseguard.backend.board.application.facade;

import jeonseguard.backend.board.application.service.BoardService;
import jeonseguard.backend.board.presentation.dto.BoardResponse;
import jeonseguard.backend.post.domain.entity.PostCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardFacade {
    private final BoardService boardService;

    public BoardResponse getBoardByCategory(String category, Pageable pageable) {
        PostCategory parsedCategory = PostCategory.valueOf(category.toUpperCase());
        return boardService.getBoardByCategory(parsedCategory, pageable);
    }
}
