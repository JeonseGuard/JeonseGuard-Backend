package jeonseguard.backend.board.application;

import jeonseguard.backend.board.domain.entity.*;
import jeonseguard.backend.board.domain.service.*;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.board.presentation.dto.response.*;
import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.domain.service.UserService;
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
        List<CommentResponse> comments = commentService.getComments(postId, userId);
        return PostInfoResponse.of(post, comments);
    }

    public CreatePostResponse createPost(Long userId, String category, CreatePostRequest request) {
        User user = userService.getUser(userId);
        Post post = postService.createPost(category, user, request);
        return CreatePostResponse.fromEntity(post);
    }

    public void updatePost(Long userId, Long postId, String category, UpdatePostRequest request) {
        User user = userService.getUser(userId);
        Post post = postService.getPost(category, postId);
        postService.updatePost(user, post, request);
    }

    public void deletePost(Long userId, Long postId, String category) {
        User user = userService.getUser(userId);
        Post post = postService.getPost(category, postId);
        postService.deletePost(user, post);
    }

    public CreateCommentResponse createComment(Long userId, Long postId, String category, CreateCommentRequest request) {
        User user = userService.getUser(userId);
        Post post = postService.getPost(category, postId);
        Comment comment = commentService.createComment(user, post, request);
        return CreateCommentResponse.fromEntity(comment);
    }

    public void updateComment(Long userId, Long commentId, UpdateCommentRequest request) {
        User user = userService.getUser(userId);
        Comment comment = commentService.getComment(commentId);
        commentService.updateComment(user, comment, request);
    }

    public void deleteComment(Long userId, Long commentId) {
        User user = userService.getUser(userId);
        Comment comment = commentService.getComment(commentId);
        commentService.deleteComment(user, comment);
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
