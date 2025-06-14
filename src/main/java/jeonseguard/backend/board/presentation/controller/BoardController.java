package jeonseguard.backend.board.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jeonseguard.backend.board.application.facade.BoardFacade;
import jeonseguard.backend.board.presentation.dto.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Board", description = "게시판 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v5/board")
public class BoardController {
    private final BoardFacade boardFacade;

    @Operation(summary = "카테고리별 게시판 조회", description = "카테고리별로 게시판을 조회합니다.")
    @GetMapping("/{category}")
    public ResponseEntity<BoardResponse> getBoardByCategory(@PathVariable String category, Pageable pageable) {
        return ResponseEntity.ok(boardFacade.getBoardByCategory(category, pageable));
    }
}
