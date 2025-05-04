package jeonseguard.backend.board.application.service;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.factory.CommentFactory;
import jeonseguard.backend.board.domain.repository.*;
import jeonseguard.backend.board.infrastructure.dto.CommentResponse;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.user.infrastructure.dto.UserDetailResponse;
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
    public List<CommentResponse> getComments(Long userId, Long postId) {
        return commentQueryRepository.findAllByIdAndPostId(userId, postId);
    }

    @Transactional(readOnly = true)
    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));
    }

    @Caching(evict = {
            @CacheEvict(value = "postPage", allEntries = true),
            @CacheEvict(value = "postDetail", key = "'post::id:' + #postId"),
            @CacheEvict(value = "commentList", key = "'comment::postId:' + #postId")
    })
    @Transactional
    public Comment createComment(Long postId, UserDetailResponse response, CreateCommentRequest request) {
        Comment comment = CommentFactory.from(postId, response, request);
        return commentRepository.save(comment);
    }

    @Caching(evict = {
            @CacheEvict(value = "postPage", allEntries = true),
            @CacheEvict(value = "postDetail", key = "'post::id:' + #comment.postId"),
            @CacheEvict(value = "commentList", key = "'comment::postId:' + #comment.postId")
    })
    @Transactional
    public void updateComment(Long userId, Comment comment, UserDetailResponse response, UpdateCommentRequest request) {
        validateCommentAuthor(userId, comment, ErrorCode.COMMENT_UPDATE_FORBIDDEN);
        comment.updateComment(request.newContent(), response.nickname());
    }

    @Caching(evict = {
            @CacheEvict(value = "postPage", allEntries = true),
            @CacheEvict(value = "postDetail", key = "'post::id:' + #comment.postId"),
            @CacheEvict(value = "commentList", key = "'comment::postId:' + #comment.postId")
    })
    @Transactional
    public void deleteComment(Long userId, Comment comment) {
        validateCommentAuthor(userId, comment, ErrorCode.COMMENT_DELETE_FORBIDDEN);
        commentRepository.delete(comment);
    }

    private void validateCommentAuthor(Long userId, Comment comment, ErrorCode errorCode) {
        if (!comment.getUserId().equals(userId)) {
            throw new BusinessException(errorCode);
        }
    }
}
