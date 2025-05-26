package jeonseguard.backend.post.application.service;

import jeonseguard.backend.global.exception.error.ErrorCode;
import jeonseguard.backend.post.domain.entity.*;
import jeonseguard.backend.post.domain.factory.PostFactory;
import jeonseguard.backend.post.domain.policy.PostPolicy;
import jeonseguard.backend.post.domain.repository.PostRepository;
import jeonseguard.backend.post.presentation.dto.request.*;
import jeonseguard.backend.user.infrastructure.dto.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static jeonseguard.backend.global.constant.CacheKey.POST_ID_PREFIX;

@Transactional
@Service
@RequiredArgsConstructor
public class PostCommandService {
    private final PostRepository postRepository;

    @CacheEvict(value = "board", allEntries = true)
    public Post createPostByCategory(PostCategory category, UserSummary userSummary, CreatePostRequest request) {
        Post post = PostFactory.from(category, userSummary, request);
        return postRepository.save(post);
    }

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "post", key = "'" + POST_ID_PREFIX + "' + #post.id")
    })
    public void updatePost(Long userId, Post post, UserSummary userSummary, UpdatePostRequest request) {
        PostPolicy.validateAuthor(userId, post, ErrorCode.POST_UPDATE_FORBIDDEN);
        post.updatePost(request.newTitle(), request.newContent(), userSummary.nickname());
    }

    @Caching(evict = {
            @CacheEvict(value = "board", allEntries = true),
            @CacheEvict(value = "post", key = "'" + POST_ID_PREFIX + "' + #post.id")
    })
    public void deletePost(Long userId, Post post) {
        PostPolicy.validateAuthor(userId, post, ErrorCode.POST_DELETE_FORBIDDEN);
        postRepository.delete(post);
    }
}
