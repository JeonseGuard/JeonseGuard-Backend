package jeonseguard.backend.post.application.service;

import jeonseguard.backend.post.domain.factory.PostFactory;
import jeonseguard.backend.post.domain.policy.PostPolicy;
import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.post.domain.entity.*;
import jeonseguard.backend.post.domain.repository.*;
import jeonseguard.backend.post.infrastructure.dto.*;
import jeonseguard.backend.post.presentation.dto.request.*;
import jeonseguard.backend.user.infrastructure.dto.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostQueryRepository postQueryRepository;

    @Cacheable(value = "postDetail", key = "'post::id:' + #postId")
    @Transactional(readOnly = true)
    public PostDetailResponse getPostDetail(Long userId, Long postId) {
        return postQueryRepository.findDetailByUserIdAndId(userId, postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
    }

    @CacheEvict(value = "board", allEntries = true)
    @Transactional
    public Post createPostByCategory(PostCategory category, UserDetailResponse response, CreatePostRequest request) {
        Post post = PostFactory.from(category, response, request);
        return postRepository.save(post);
    }

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "postDetail", key = "'post::id:' + #post.id")
    })
    @Transactional
    public void updatePost(Long userId, Post post, UserDetailResponse response, UpdatePostRequest request) {
        PostPolicy.validateAuthor(userId, post, ErrorCode.POST_UPDATE_FORBIDDEN);
        post.updatePost(request.newTitle(), request.newContent(), response.nickname());
    }

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "postDetail", key = "'post::id:' + #post.id")
    })
    @Transactional
    public void deletePost(Long userId, Post post) {
        PostPolicy.validateAuthor(userId, post, ErrorCode.POST_DELETE_FORBIDDEN);
        postRepository.delete(post);
    }
}
