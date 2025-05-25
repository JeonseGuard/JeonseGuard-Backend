package jeonseguard.backend.comment.application.facade;

import jeonseguard.backend.comment.domain.entity.Comment;
import jeonseguard.backend.comment.application.service.*;
import jeonseguard.backend.comment.presentation.dto.request.*;
import jeonseguard.backend.comment.presentation.dto.response.CreateCommentResponse;
import jeonseguard.backend.user.application.service.UserReadService;
import jeonseguard.backend.user.infrastructure.dto.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentFacade {
    private final CommentReadService commentReadService;
    private final CommentWriteService commentWriteService;
    private final UserReadService userService;

    public CreateCommentResponse createComment(Long userId, CreateCommentRequest request) {
        UserSummary userSummary = userService.getUserSummary(userId);
        Comment comment = commentWriteService.createComment(userSummary, request);
        return CreateCommentResponse.from(comment);
    }

    public void updateComment(Long userId, Long commentId, UpdateCommentRequest request) {
        UserSummary userSummary = userService.getUserSummary(userId);
        Comment comment = commentReadService.getComment(commentId);
        commentWriteService.updateComment(userId, comment, userSummary, request);
    }

    public void deleteComment(Long userId, Long commentId) {
        Comment comment = commentReadService.getComment(commentId);
        commentWriteService.deleteComment(userId, comment);
    }
}
