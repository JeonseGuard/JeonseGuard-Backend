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

    public PostPageResponse getPosts(String category, Pageable pageable) {
        Page<Post> pages = postService.getPosts(category, pageable);
        List<PostResponse> posts = toPostResponses(pages.getContent());
        return PostPageResponse.of(posts, pages);
    }

    public PostInfoResponse getPost(String category, Long postId, Long userId) {
        User user = userService.getUser(userId);
        Post post = postService.getPost(category, postId);
        List<CommentResponse> comments = toCommentResponses(postId, user);
        HeartResponse heart = toHeartResponse(postId, HeartTarget.POST, user);
        return PostInfoResponse.of(post, comments, heart);
    }

    public CreatePostResponse createPost(String category, Long userId, CreatePostRequest request) {
        User user = userService.getUser(userId);
        Post post = postService.createPost(category, user, request);
        return CreatePostResponse.fromEntity(post);
    }

    public void updatePost(String category, Long userId, Long postId, UpdatePostRequest request) {
        User user = userService.getUser(userId);
        Post post = postService.getPost(category, postId);
        postService.updatePost(user, post, request);
    }

    public void deletePost(String category, Long userId, Long postId) {
        User user = userService.getUser(userId);
        Post post = postService.getPost(category, postId);
        postService.deletePost(user, post);
    }

    public CreateCommentResponse createComment(String category, Long userId, Long postId, CreateCommentRequest request) {
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

    public HeartResponse changePostHeart(Long userId, Long targetId) {
        User user = userService.getUser(userId);
        return changeHeart(targetId, HeartTarget.POST, user);
    }

    public HeartResponse changeCommentHeart(Long userId, Long targetId) {
        User user = userService.getUser(userId);
        return changeHeart(targetId, HeartTarget.COMMENT, user);
    }

    private List<PostResponse> toPostResponses(List<Post> posts) {
        return posts.stream()
                .map(this::toPostResponse)
                .toList();
    }

    private List<CommentResponse> toCommentResponses(Long postId, User user) {
        return commentService.getComments(postId).stream()
                .map(comment -> toCommentResponse(comment, user))
                .toList();
    }

    private PostResponse toPostResponse(Post post) {
        long commentCount = commentService.countComments(post.getId());
        long heartCount = heartService.countHearts(post.getId(), HeartTarget.POST);
        return PostResponse.of(post, commentCount, heartCount);
    }

    private CommentResponse toCommentResponse(Comment comment, User user) {
        HeartResponse heart = toHeartResponse(comment.getId(), HeartTarget.COMMENT, user);
        return CommentResponse.of(comment, heart);
    }

    private HeartResponse toHeartResponse(Long targetId, HeartTarget target, User user) {
        long heartCount = heartService.countHearts(targetId, target);
        boolean heartStatus = heartService.checkHeartStatus(targetId, target, user);
        return HeartResponse.of(heartCount, heartStatus);
    }

    private HeartResponse changeHeart(Long targetId, HeartTarget target, User user) {
        boolean heartStatus = heartService.checkHeartStatus(targetId, target, user);
        heartService.changeHeart(heartStatus, targetId, target, user);
        long heartCount = heartService.countHearts(targetId, target);
        return HeartResponse.of(heartCount, !heartStatus);
    }
}
