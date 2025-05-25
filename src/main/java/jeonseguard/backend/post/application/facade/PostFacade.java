package jeonseguard.backend.post.application.facade;

import jeonseguard.backend.comment.application.service.*;
import jeonseguard.backend.comment.infrastructure.dto.CommentResponse;
import jeonseguard.backend.post.application.service.*;
import jeonseguard.backend.post.domain.entity.*;
import jeonseguard.backend.post.infrastructure.dto.*;
import jeonseguard.backend.post.presentation.dto.request.*;
import jeonseguard.backend.post.presentation.dto.response.*;
import jeonseguard.backend.user.application.service.UserQueryService;
import jeonseguard.backend.user.infrastructure.dto.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostFacade {
    private final PostQueryService postQueryService;
    private final PostCommandService postCommandService;
    private final CommentQueryService commentQueryService;
    private final CommentCommandService commentCommandService;
    private final UserQueryService userReadService;

    public PostInfoResponse getPostInfo(Long userId, Long postId) {
        PostSummary postSummary = postQueryService.getPostSummary(userId, postId);
        List<CommentResponse> comments = commentQueryService.getComments(postId);
        return PostInfoResponse.of(postSummary, comments);
    }

    public CreatePostResponse createPostByCategory(Long userId, String category, CreatePostRequest request) {
        UserSummary userSummary = userReadService.getUserSummary(userId);
        PostCategory parsedCategory = PostCategory.valueOf(category.toUpperCase());
        Post post = postCommandService.createPostByCategory(parsedCategory, userSummary, request);
        return CreatePostResponse.from(post);
    }

    public void updatePost(Long userId, Long postId, UpdatePostRequest request) {
        UserSummary userSummary = userReadService.getUserSummary(userId);
        Post post = postQueryService.getPost(postId);
        postCommandService.updatePost(userId, post, userSummary, request);
    }

    public void deletePost(Long userId, Long postId) {
        commentCommandService.deleteAllByPostId(postId);
        Post post = postQueryService.getPost(postId);
        postCommandService.deletePost(userId, post);
    }
}
