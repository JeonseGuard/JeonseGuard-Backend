package jeonseguard.backend.post.application.facade;

import jeonseguard.backend.comment.application.service.CommentService;
import jeonseguard.backend.comment.infrastructure.dto.CommentResponse;
import jeonseguard.backend.post.application.service.PostService;
import jeonseguard.backend.post.domain.entity.*;
import jeonseguard.backend.post.infrastructure.dto.*;
import jeonseguard.backend.post.presentation.dto.request.*;
import jeonseguard.backend.post.presentation.dto.response.*;
import jeonseguard.backend.user.application.service.UserService;
import jeonseguard.backend.user.infrastructure.dto.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostFacade {
    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;

    @Cacheable(value = "postInfo", key = "'post::id:' + #postId")
    public PostInfoResponse getPostInfo(Long userId, Long postId) {
        PostDetailResponse response = postService.getPostDetail(userId, postId);
        List<CommentResponse> comments = commentService.getComments(postId);
        return PostInfoResponse.of(response, comments);
    }

    public CreatePostResponse createPostByCategory(Long userId, String category, CreatePostRequest request) {
        UserDetailResponse response = userService.getUserDetail(userId);
        PostCategory parsedCategory = PostCategory.valueOf(category.toUpperCase());
        Post post = postService.createPostByCategory(parsedCategory, response, request);
        return CreatePostResponse.fromEntity(post);
    }

    public void updatePost(Long userId, Long postId, UpdatePostRequest request) {
        UserDetailResponse response = userService.getUserDetail(userId);
        Post post = postService.getPost(postId);
        postService.updatePost(userId, post, response, request);
    }

    public void deletePost(Long userId, Long postId) {
        commentService.deleteAllByPostId(postId);
        Post post = postService.getPost(postId);
        postService.deletePost(userId, post);
    }
}
