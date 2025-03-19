package jeonseguard.backend.board.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jeonseguard.backend.auth.domain.annotation.AuthenticatedUser;
import jeonseguard.backend.board.application.BoardFacade;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.board.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Board", description = "게시판 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {
    private final BoardFacade boardFacade;

    @Operation(summary = "게시글 전체 조회", description = "모든 게시글을 페이지네이션과 함깨 조회합니다.")
    @GetMapping
    public ResponseEntity<BoardPageResponse> getBoards(Pageable pageable) {
        return ResponseEntity.ok(boardFacade.getBoards(pageable));
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글 ID를 이용하여, 특정 게시글을 조회합니다.")
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardInfoResponse> getBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardFacade.getBoard(boardId));
    }

    @Operation(summary = "게시글 생성", description = "게시글을 생성합니다.")
    @PostMapping
    public ResponseEntity<CreateBoardResponse> createBoard(@AuthenticatedUser Long userId, @Valid @RequestBody CreateBoardRequest request) {
        return ResponseEntity.ok(boardFacade.createBoard(userId, request));
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @PutMapping("/{boardId}")
    public ResponseEntity<Void> updateBoard(@PathVariable Long boardId, @AuthenticatedUser Long userId, @Valid @RequestBody UpdateBoardRequest request) {
        boardFacade.updateBoard(boardId, userId, request);
        return ResponseEntity.noContent().build();
    }
}
