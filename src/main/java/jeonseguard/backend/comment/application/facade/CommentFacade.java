package jeonseguard.backend.comment.application.facade;

import jeonseguard.backend.comment.application.service.CommentService;
import jeonseguard.backend.comment.domain.entity.Comment;
import jeonseguard.backend.comment.presentation.dto.request.*;
import jeonseguard.backend.comment.presentation.dto.response.CreateCommentResponse;
import jeonseguard.backend.user.application.service.UserService;
import jeonseguard.backend.user.infrastructure.dto.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentFacade {
    private final CommentService commentService;
    private final UserService userService;

    public CreateCommentResponse createComment(Long userId, CreateCommentRequest request) {
        UserDetailResponse response = userService.getUserDetail(userId);
        Comment comment = commentService.createComment(response, request);
        return CreateCommentResponse.fromEntity(comment);
    }

    public void updateComment(Long userId, Long commentId, UpdateCommentRequest request) {
        UserDetailResponse response = userService.getUserDetail(userId);
        Comment comment = commentService.getComment(commentId);
        commentService.updateComment(userId, comment, response, request);
    }

    public void deleteComment(Long userId, Long commentId) {
        Comment comment = commentService.getComment(commentId);
        commentService.deleteComment(userId, comment);
    }
}
