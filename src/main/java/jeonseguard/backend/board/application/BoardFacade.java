package jeonseguard.backend.board.application;

import jeonseguard.backend.board.domain.entity.Post;
import jeonseguard.backend.board.domain.service.PostService;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.board.presentation.dto.response.*;
import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardFacade {
    private final PostService postService;
    private final UserService userService;

    public PostPageResponse getPosts(String category, Pageable pageable) {
        Page<Post> boards = postService.getPosts(category, pageable);
        return PostPageResponse.of(boards);
    }

    public PostInfoResponse getPost(String category, Long postId) {
        Post post = postService.getPost(category, postId);
        return PostInfoResponse.fromEntity(post);
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
}
