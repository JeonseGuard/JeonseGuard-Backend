package jeonseguard.backend.comment.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jeonseguard.backend.comment.application.facade.CommentFacade;
import jeonseguard.backend.comment.presentation.dto.request.*;
import jeonseguard.backend.comment.presentation.dto.response.CreateCommentResponse;
import jeonseguard.backend.global.annotation.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comment", description = "댓글 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v5/comment")
public class CommentController {
    private final CommentFacade commentFacade;

    @Operation(summary = "댓글 생성", description = "댓글을 생성합니다.")
    @PostMapping()
    public ResponseEntity<CreateCommentResponse> createComment(@AuthenticatedUser Long userId, @Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.ok(commentFacade.createComment(userId, request));
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@AuthenticatedUser Long userId, @PathVariable Long commentId, @Valid @RequestBody UpdateCommentRequest request) {
        commentFacade.updateComment(userId, commentId, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@AuthenticatedUser Long userId, @PathVariable Long commentId) {
        commentFacade.deleteComment(userId, commentId);
        return ResponseEntity.noContent().build();
    }
}
