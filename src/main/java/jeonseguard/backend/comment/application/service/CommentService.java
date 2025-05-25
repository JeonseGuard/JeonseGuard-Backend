package jeonseguard.backend.comment.application.service;

import jeonseguard.backend.comment.domain.factory.CommentFactory;
import jeonseguard.backend.comment.domain.policy.CommentPolicy;
import jeonseguard.backend.comment.infrastructure.dto.CommentResponse;
import jeonseguard.backend.comment.domain.entity.Comment;
import jeonseguard.backend.comment.domain.repository.*;
import jeonseguard.backend.comment.presentation.dto.request.*;
import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.user.infrastructure.dto.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;

    @Cacheable(value = "commentList", key = "'comment::postId:' + #postId")
    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Long postId) {
        return commentQueryRepository.findAllByPostId(postId);
    }

    @Transactional(readOnly = true)
    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));
    }

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "postDetail", key = "'post::id:' + #request.postId()"),
            @CacheEvict(value = "commentList", key = "'comment::postId:' + #request.postId()")
    })
    @Transactional
    public Comment createComment(UserSummary userSummary, CreateCommentRequest request) {
        Comment comment = CommentFactory.from(userSummary, request);
        return commentRepository.save(comment);
    }

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "postDetail", key = "'post::id:' + #comment.postId"),
            @CacheEvict(value = "commentList", key = "'comment::postId:' + #comment.postId")
    })
    @Transactional
    public void updateComment(Long userId, Comment comment, UserSummary userSummary, UpdateCommentRequest request) {
        CommentPolicy.validateAuthor(userId, comment, ErrorCode.COMMENT_UPDATE_FORBIDDEN);
        comment.updateComment(request.newContent(), userSummary.nickname());
    }

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "postDetail", key = "'post::id:' + #comment.postId"),
            @CacheEvict(value = "commentList", key = "'comment::postId:' + #comment.postId")
    })
    @Transactional
    public void deleteComment(Long userId, Comment comment) {
        CommentPolicy.validateAuthor(userId, comment, ErrorCode.COMMENT_DELETE_FORBIDDEN);
        commentRepository.delete(comment);
    }

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "postDetail", key = "'post::id:' + #postId"),
            @CacheEvict(value = "commentList", key = "'comment::postId:' + #postId")
    })
    @Transactional
    public void deleteAllByPostId(Long postId) {
        commentQueryRepository.deleteAllByPostId(postId);
    }
}
