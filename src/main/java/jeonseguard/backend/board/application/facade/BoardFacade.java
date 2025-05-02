package jeonseguard.backend.board.application.facade;

import jeonseguard.backend.board.application.service.*;
import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.board.presentation.dto.response.*;
import jeonseguard.backend.user.application.service.UserService;
import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.infrastructure.dto.UserInfoResponse;
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

    public Page<PostResponse> getPosts(String category, Pageable pageable) {
        return postService.getPosts(category, pageable);
    }

    public PostInfoResponse getPost(Long userId, Long postId, String category) {
        PostDetailResponse post = postService.getPostDetail(userId, postId, category);
        List<CommentResponse> comments = commentService.getComments(userId, postId);
        return PostInfoResponse.of(post, comments);
    }

    public CreatePostResponse createPost(Long userId, String category, CreatePostRequest request) {
        UserInfoResponse response = userService.getUserInfo(userId);
        Post post = postService.createPost(category, response, request);
        return CreatePostResponse.fromEntity(post);
    }

    public void updatePost(Long userId, Long postId, String category, UpdatePostRequest request) {
        User user = userService.getUser(userId);
        Post post = postService.getPost(userId, postId, category);
        postService.updatePost(userId, user, post, request);
    }

    public void deletePost(Long userId, Long postId, String category) {
        Post post = postService.getPost(userId, postId, category);
        postService.deletePost(userId, post);
    }

    public CreateCommentResponse createComment(Long userId, Long postId, CreateCommentRequest request) {
        UserInfoResponse user = userService.getUserInfo(userId);
        Comment comment = commentService.createComment(postId, user, request);
        return CreateCommentResponse.fromEntity(comment);
    }

    public void updateComment(Long userId, Long commentId, UpdateCommentRequest request) {
        UserInfoResponse user = userService.getUserInfo(userId);
        Comment comment = commentService.getComment(commentId);
        commentService.updateComment(userId, comment, user, request);
    }

    public void deleteComment(Long userId, Long commentId) {
        Comment comment = commentService.getComment(commentId);
        commentService.deleteComment(userId, comment);
    }

    public HeartResponse togglePostHeart(Long userId, Long targetId) {
        return toggleHeart(userId, targetId, HeartTarget.POST);
    }

    public HeartResponse toggleCommentHeart(Long userId, Long targetId) {
        return toggleHeart(userId, targetId, HeartTarget.COMMENT);
    }

    private HeartResponse toggleHeart(Long userId, Long targetId, HeartTarget target) {
        heartService.toggleHeart(userId, targetId, target);
        long heartCount = heartService.countHeart(targetId, target);
        boolean heartStatus = heartService.hasHeart(userId, targetId, target);
        return HeartResponse.of(heartCount, heartStatus);
    }
}
