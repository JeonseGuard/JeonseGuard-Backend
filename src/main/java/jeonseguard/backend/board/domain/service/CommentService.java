package jeonseguard.backend.board.domain.service;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.factory.CommentFactory;
import jeonseguard.backend.board.domain.repository.CommentRepository;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));
    }

    @Transactional
    public Comment createComment(User user, Post post, CreateCommentRequest request) {
        Comment comment = CommentFactory.fromRequest(user, post, request);
        return commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(User user, Comment comment, UpdateCommentRequest request) {
        validateCommentAuthor(user, comment, ErrorCode.COMMENT_UPDATE_FORBIDDEN);
        comment.updateComment(request.newContent(), user.getNickname());
    }

    @Transactional
    public void deleteComment(User user, Comment comment) {
        validateCommentAuthor(user, comment, ErrorCode.COMMENT_DELETE_FORBIDDEN);
        commentRepository.delete(comment);
    }

    private void validateCommentAuthor(User user, Comment comment, ErrorCode errorCode) {
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new BusinessException(errorCode);
        }
    }
}
