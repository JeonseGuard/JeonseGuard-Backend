package jeonseguard.backend.board.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jeonseguard.backend.board.application.facade.BoardFacade;
import jeonseguard.backend.global.annotation.AuthenticatedUser;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.board.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Board", description = "게시판 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v4/board")
public class BoardController {
    private final BoardFacade boardFacade;

    @Operation(summary = "카테고리별 게시글 전체 조회", description = "카테고리별 게시글을 페이지네이션 방식으로 전체 조회합니다.")
    @GetMapping("/{category}")
    public ResponseEntity<PostPageResponse> getPostPageByCategory(@PathVariable String category, Pageable pageable) {
        return ResponseEntity.ok(boardFacade.getPostPageByCategory(category, pageable));
    }

    @Operation(summary = "카테고리별 게시글 생성", description = "카테고리별 게시글을 생성합니다.")
    @PostMapping("/{category}/posts")
    public ResponseEntity<CreatePostResponse> createPostByCategory(@AuthenticatedUser Long userId, @PathVariable String category, @Valid @RequestBody CreatePostRequest request) {
        return ResponseEntity.ok(boardFacade.createPostByCategory(userId, category, request));
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글 ID를 이용하여, 게시글을 상세 조회합니다.")
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostInfoResponse> getPostInfo(@AuthenticatedUser Long userId, @PathVariable Long postId) {
        return ResponseEntity.ok(boardFacade.getPostInfo(userId, postId));
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @PatchMapping("/posts/{postId}")
    public ResponseEntity<Void> updatePost(@AuthenticatedUser Long userId, @PathVariable Long postId, @RequestBody UpdatePostRequest request) {
        boardFacade.updatePost(userId, postId, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@AuthenticatedUser Long userId, @PathVariable Long postId) {
        boardFacade.deletePost(userId, postId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "게시글 좋아요", description = "게시글에 좋아요를 누르거나 취소합니다.")
    @PostMapping("/posts/{postId}/hearts")
    public ResponseEntity<HeartResponse> togglePostHeart(@AuthenticatedUser Long userId, @PathVariable Long postId) {
        return ResponseEntity.ok(boardFacade.togglePostHeart(userId, postId));
    }

    @Operation(summary = "댓글 생성", description = "댓글을 생성합니다.")
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(@AuthenticatedUser Long userId, @PathVariable Long postId, @Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.ok(boardFacade.createComment(userId, postId, request));
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@AuthenticatedUser Long userId, @PathVariable Long commentId, @Valid @RequestBody UpdateCommentRequest request) {
        boardFacade.updateComment(userId, commentId, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@AuthenticatedUser Long userId, @PathVariable Long commentId) {
        boardFacade.deleteComment(userId, commentId);
        return ResponseEntity.noContent().build();
    }
}
