package jeonseguard.backend.board.application.service;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.factory.PostFactory;
import jeonseguard.backend.board.domain.repository.*;
import jeonseguard.backend.board.infrastructure.dto.*;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.user.infrastructure.dto.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostQueryRepository postQueryRepository;

    @Transactional(readOnly = true)
    public Page<PostResponse> getPostPageByCategory(String category, Pageable pageable) {
        return postQueryRepository.findAllWithCounts(parseCategory(category), pageable);
    }

    @Cacheable(value = "postDetail", key = "'post::id:' + #postId")
    @Transactional(readOnly = true)
    public PostDetailResponse getPostDetail(Long userId, Long postId) {
        return postQueryRepository.findDetailByUserIdAndId(userId, postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Post getPostByCategory(Long userId, Long postId, String category) {
        return postQueryRepository.findByUserIdAndIdAndCategory(userId, postId, parseCategory(category))
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
    }

    @CacheEvict(value = "postPage", allEntries = true)
    @Transactional
    public Post createPostByCategory(String category, UserDetailResponse response, CreatePostRequest request) {
        Post post = PostFactory.from(parseCategory(category), response, request);
        return postRepository.save(post);
    }

    @Caching(evict = {
            @CacheEvict(value = "postPage", allEntries = true),
            @CacheEvict(value = "postDetail", key = "'post::id:' + #post.id")
    })
    @Transactional
    public void updatePost(Long userId, Post post, UserDetailResponse response, UpdatePostRequest request) {
        validatePostAuthor(userId, post, ErrorCode.POST_UPDATE_FORBIDDEN);
        post.updatePost(request.newTitle(), request.newContent(), response.nickname());
    }

    @Caching(evict = {
            @CacheEvict(value = "postPage", allEntries = true),
            @CacheEvict(value = "postDetail", key = "'post::id:' + #post.id")
    })
    @Transactional
    public void deletePost(Long userId, Post post) {
        validatePostAuthor(userId, post, ErrorCode.POST_DELETE_FORBIDDEN);
        postRepository.delete(post);
    }

    private void validatePostAuthor(Long userId, Post post, ErrorCode errorCode) {
        if (!post.getUserId().equals(userId)) {
            throw new BusinessException(errorCode);
        }
    }

    private BoardCategory parseCategory(String category) {
        return BoardCategory.valueOf(category.toUpperCase());
    }
}
