package jeonseguard.backend.comment.application.service;

import jeonseguard.backend.comment.domain.entity.Comment;
import jeonseguard.backend.comment.domain.factory.CommentFactory;
import jeonseguard.backend.comment.domain.policy.CommentPolicy;
import jeonseguard.backend.comment.domain.repository.*;
import jeonseguard.backend.comment.presentation.dto.request.*;
import jeonseguard.backend.global.exception.error.ErrorCode;
import jeonseguard.backend.user.infrastructure.dto.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentWriteService {
    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "post", key = "'post::id:' + #request.postId()"),
            @CacheEvict(value = "comments", key = "'comment::postId:' + #request.postId()")
    })

    public Comment createComment(UserSummary userSummary, CreateCommentRequest request) {
        Comment comment = CommentFactory.from(userSummary, request);
        return commentRepository.save(comment);
    }

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "post", key = "'post::id:' + #comment.postId"),
            @CacheEvict(value = "comments", key = "'comment::postId:' + #comment.postId")
    })
    public void updateComment(Long userId, Comment comment, UserSummary userSummary, UpdateCommentRequest request) {
        CommentPolicy.validateAuthor(userId, comment, ErrorCode.COMMENT_UPDATE_FORBIDDEN);
        comment.updateComment(request.newContent(), userSummary.nickname());
    }

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "post", key = "'post::id:' + #comment.postId"),
            @CacheEvict(value = "comments", key = "'comment::postId:' + #comment.postId")
    })
    public void deleteComment(Long userId, Comment comment) {
        CommentPolicy.validateAuthor(userId, comment, ErrorCode.COMMENT_DELETE_FORBIDDEN);
        commentRepository.delete(comment);
    }

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "post", key = "'post::id:' + #postId"),
            @CacheEvict(value = "comments", key = "'comment::postId:' + #postId")
    })
    public void deleteAllByPostId(Long postId) {
        commentQueryRepository.deleteAllByPostId(postId);
    }
}
