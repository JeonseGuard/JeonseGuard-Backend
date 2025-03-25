package jeonseguard.backend.board.domain.service;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.factory.PostFactory;
import jeonseguard.backend.board.domain.repository.*;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.board.presentation.dto.response.*;
import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostQueryRepository postQueryRepository;

    @Transactional(readOnly = true)
    public Page<PostResponse> getPosts(String category, Pageable pageable) {
        return postQueryRepository.findAllWithCounts(parseCategory(category), pageable);
    }

    @Transactional(readOnly = true)
    public PostDetailResponse getPostDetail(Long userId, Long postId, String category) {
        return Optional.ofNullable(postQueryRepository.findDetailById(userId, postId, category))
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Post getPost(String category, Long postId) {
        return postRepository.findByCategoryAndId(parseCategory(category), postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
    }

    @Transactional
    public Post createPost(String category, User user, CreatePostRequest request) {
        Post post = PostFactory.fromRequest(parseCategory(category), user, request);
        return postRepository.save(post);
    }

    @Transactional
    public void updatePost(User user, Post post, UpdatePostRequest request) {
        validatePostAuthor(user, post, ErrorCode.POST_UPDATE_FORBIDDEN);
        post.updatePost(request.newTitle(), request.newContent(), user.getNickname());
    }

    @Transactional
    public void deletePost(User user, Post post) {
        validatePostAuthor(user, post, ErrorCode.POST_DELETE_FORBIDDEN);
        postRepository.delete(post);
    }

    private void validatePostAuthor(User user, Post post, ErrorCode errorCode) {
        if (!post.getUser().getId().equals(user.getId())) {
            throw new BusinessException(errorCode);
        }
    }

    private BoardCategory parseCategory(String category) {
        return BoardCategory.valueOf(category.toUpperCase());
    }
}
