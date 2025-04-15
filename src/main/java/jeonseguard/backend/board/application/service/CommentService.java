package jeonseguard.backend.board.application.service;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.factory.CommentFactory;
import jeonseguard.backend.board.domain.repository.*;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.board.presentation.dto.response.CommentResponse;
import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;

    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Long userId, Long postId) {
        return commentQueryRepository.findAllByIdAndPostIdAndTarget(userId, postId, HeartTarget.COMMENT);
    }

    @Transactional(readOnly = true)
    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));
    }

    @Transactional
    public Comment createComment(Long postId, User user, CreateCommentRequest request) {
        Comment comment = CommentFactory.fromRequest(postId, user, request);
        return commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long userId, User user, Comment comment, UpdateCommentRequest request) {
        validateCommentAuthor(userId, comment, ErrorCode.COMMENT_UPDATE_FORBIDDEN);
        comment.updateComment(request.newContent(), user.getNickname());
    }

    @Transactional
    public void deleteComment(Long userId, Comment comment) {
        validateCommentAuthor(userId, comment, ErrorCode.COMMENT_DELETE_FORBIDDEN);
        commentRepository.delete(comment);
    }

    private void validateCommentAuthor(Long userId, Comment comment, ErrorCode errorCode) {
        if (!comment.getUser().getId().equals(userId)) {
            throw new BusinessException(errorCode);
        }
    }
}
