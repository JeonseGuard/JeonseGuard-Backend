package jeonseguard.backend.board.application.facade;

import jeonseguard.backend.board.application.service.*;
import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.infrastructure.dto.*;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.board.presentation.dto.response.*;
import jeonseguard.backend.user.application.service.UserService;
import jeonseguard.backend.user.infrastructure.dto.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardFacade {
    private final PostService postService;
    private final CommentService commentService;
    private final HeartService heartService;
    private final UserService userService;

    @Cacheable(value = "postPage", key = "'post::category:' + #category + ':page:' + #pageable.pageNumber")
    public PostPageResponse getPostPageByCategory(String category, Pageable pageable) {
        Page<PostResponse> page = postService.getPostPageByCategory(category, pageable);
        return PostPageResponse.from(page);
    }

    @Cacheable(value = "postInfo", key = "'post::id:' + #postId")
    public PostInfoResponse getPostInfo(Long userId, Long postId) {
        PostDetailResponse response = postService.getPostDetail(userId, postId);
        List<CommentResponse> comments = commentService.getComments(postId);
        return PostInfoResponse.of(response, comments);
    }

    public CreatePostResponse createPostByCategory(Long userId, String category, CreatePostRequest request) {
        UserDetailResponse response = userService.getUserDetail(userId);
        Post post = postService.createPostByCategory(category, response, request);
        return CreatePostResponse.fromEntity(post);
    }

    public void updatePost(Long userId, Long postId, UpdatePostRequest request) {
        UserDetailResponse response = userService.getUserDetail(userId);
        Post post = postService.getPost(postId);
        postService.updatePost(userId, post, response, request);
    }

    public void deletePost(Long userId, Long postId) {
        Post post = postService.getPost(postId);
        postService.deletePost(userId, post);
    }

    public CreateCommentResponse createComment(Long userId, Long postId, CreateCommentRequest request) {
        UserDetailResponse response = userService.getUserDetail(userId);
        Comment comment = commentService.createComment(postId, response, request);
        return CreateCommentResponse.fromEntity(comment);
    }

    public void updateComment(Long userId, Long commentId, UpdateCommentRequest request) {
        UserDetailResponse response = userService.getUserDetail(userId);
        Comment comment = commentService.getComment(commentId);
        commentService.updateComment(userId, comment, response, request);
    }

    public void deleteComment(Long userId, Long commentId) {
        Comment comment = commentService.getComment(commentId);
        commentService.deleteComment(userId, comment);
    }

    public HeartResponse togglePostHeart(Long userId, Long postId) {
        heartService.toggleHeart(userId, postId);
        long heartCount = heartService.countHeart(postId);
        boolean heartStatus = heartService.hasHeart(userId, postId);
        return HeartResponse.of(heartCount, heartStatus);
    }
}
