package jeonseguard.backend.comment.application.service;

import jeonseguard.backend.comment.domain.entity.Comment;
import jeonseguard.backend.comment.domain.repository.*;
import jeonseguard.backend.comment.infrastructure.dto.CommentResponse;
import jeonseguard.backend.global.exception.error.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CommentReadService {
    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;

    @Cacheable(value = "comments", key = "'comment::postId:' + #postId")
    public List<CommentResponse> getComments(Long postId) {
        return commentQueryRepository.findAllByPostId(postId);
    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));
    }
}
