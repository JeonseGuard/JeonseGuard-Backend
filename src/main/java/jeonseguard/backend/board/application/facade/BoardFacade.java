package jeonseguard.backend.board.application.facade;

import jeonseguard.backend.board.application.service.*;
import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.infrastructure.dto.*;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.board.presentation.dto.response.*;
import jeonseguard.backend.user.application.service.UserService;
import jeonseguard.backend.user.infrastructure.dto.UserDetailResponse;
import lombok.RequiredArgsConstructor;
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

    public PostPageResponse getPostPage(String category, Pageable pageable) {
        return postService.getPostPage(category, pageable);
    }

    public PostInfoResponse getPost(Long userId, Long postId, String category) {
        PostDetailResponse response = postService.getPostDetail(userId, postId, category);
        List<CommentResponse> comments = commentService.getComments(userId, postId);
        return PostInfoResponse.of(response, comments);
    }

    public CreatePostResponse createPost(Long userId, String category, CreatePostRequest request) {
        UserDetailResponse response = userService.getUserDetail(userId);
        Post post = postService.createPost(category, response, request);
        return CreatePostResponse.fromEntity(post);
    }

    public void updatePost(Long userId, Long postId, String category, UpdatePostRequest request) {
        UserDetailResponse response = userService.getUserDetail(userId);
        Post post = postService.getPost(userId, postId, category);
        postService.updatePost(userId, post, response, request);
    }

    public void deletePost(Long userId, Long postId, String category) {
        Post post = postService.getPost(userId, postId, category);
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
