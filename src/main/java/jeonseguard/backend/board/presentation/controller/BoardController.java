package jeonseguard.backend.board.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jeonseguard.backend.global.annotation.AuthenticatedUser;
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
    @GetMapping("/{category}")
    public ResponseEntity<PostPageResponse> getPosts(@PathVariable String category,
                                                     Pageable pageable) {
        return ResponseEntity.ok(boardFacade.getPosts(category, pageable));
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글 ID를 이용하여, 특정 게시글을 조회합니다.")
    @GetMapping("/{category}/{postId}")
    public ResponseEntity<PostInfoResponse> getPost(@PathVariable String category,
                                                    @PathVariable Long postId) {
        return ResponseEntity.ok(boardFacade.getPost(category, postId));
    }

    @Operation(summary = "게시글 생성", description = "게시글을 생성합니다.")
    @PostMapping("/{category}/posts")
    public ResponseEntity<CreatePostResponse> createPost(@PathVariable String category,
                                                         @AuthenticatedUser Long userId,
                                                         @Valid @RequestBody CreatePostRequest request) {
        return ResponseEntity.ok(boardFacade.createPost(category, userId, request));
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @PatchMapping("/{category}/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable String category,
                                           @PathVariable Long postId,
                                           @AuthenticatedUser Long userId,
                                           @RequestBody UpdatePostRequest request) {
        boardFacade.updatePost(category, userId, postId, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @DeleteMapping("/{category}/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable String category,
                                           @PathVariable Long postId,
                                           @AuthenticatedUser Long userId) {
        boardFacade.deletePost(category, userId, postId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "댓글 생성", description = "댓글을 생성합니다.")
    @PostMapping("/{category}/{postId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(@PathVariable String category,
                                                               @PathVariable Long postId,
                                                               @AuthenticatedUser Long userId,
                                                               @Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.ok(boardFacade.createComment(category, userId, postId, request));
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long commentId,
                                              @AuthenticatedUser Long userId,
                                              @RequestBody UpdateCommentRequest request) {
        boardFacade.updateComment(userId, commentId, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId,
                                              @AuthenticatedUser Long userId) {
        boardFacade.deleteComment(userId, commentId);
        return ResponseEntity.noContent().build();
    }
}
