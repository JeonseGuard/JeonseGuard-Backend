package jeonseguard.backend.post.application.facade;

import jeonseguard.backend.comment.application.service.*;
import jeonseguard.backend.comment.infrastructure.dto.CommentResponse;
import jeonseguard.backend.post.application.service.PostService;
import jeonseguard.backend.post.domain.entity.*;
import jeonseguard.backend.post.infrastructure.dto.*;
import jeonseguard.backend.post.presentation.dto.request.*;
import jeonseguard.backend.post.presentation.dto.response.*;
import jeonseguard.backend.user.application.service.UserReadService;
import jeonseguard.backend.user.infrastructure.dto.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostFacade {
    private final PostService postService;
    private final CommentReadService commentReadService;
    private final CommentWriteService commentWriteService;
    private final UserReadService userReadService;

    public PostInfoResponse getPostInfo(Long userId, Long postId) {
        PostDetailResponse response = postService.getPostDetail(userId, postId);
        List<CommentResponse> comments = commentReadService.getComments(postId);
        return PostInfoResponse.of(response, comments);
    }

    public CreatePostResponse createPostByCategory(Long userId, String category, CreatePostRequest request) {
        UserSummary userSummary = userReadService.getUserSummary(userId);
        PostCategory parsedCategory = PostCategory.valueOf(category.toUpperCase());
        Post post = postService.createPostByCategory(parsedCategory, userSummary, request);
        return CreatePostResponse.fromEntity(post);
    }

    public void updatePost(Long userId, Long postId, UpdatePostRequest request) {
        UserSummary userSummary = userReadService.getUserSummary(userId);
        Post post = postService.getPost(postId);
        postService.updatePost(userId, post, userSummary, request);
    }

    public void deletePost(Long userId, Long postId) {
        commentWriteService.deleteAllByPostId(postId);
        Post post = postService.getPost(postId);
        postService.deletePost(userId, post);
    }
}
