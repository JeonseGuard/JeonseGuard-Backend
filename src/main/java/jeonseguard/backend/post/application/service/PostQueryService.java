package jeonseguard.backend.post.application.service;

import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.post.domain.entity.Post;
import jeonseguard.backend.post.domain.repository.*;
import jeonseguard.backend.post.infrastructure.dto.PostSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static jeonseguard.backend.global.constant.CacheKey.POST_ID_PREFIX;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PostQueryService {
    private final PostRepository postRepository;
    private final PostQueryRepository postQueryRepository;

    @Cacheable(value = "post", key = "'" + POST_ID_PREFIX + "' + #postId")
    public PostSummary getPostSummary(Long userId, Long postId) {
        return postQueryRepository.findByUserIdAndId(userId, postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_SUMMARY_NOT_FOUND));
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
    }
}
