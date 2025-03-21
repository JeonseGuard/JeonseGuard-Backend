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

    public PostPageResponse getPosts(Pageable pageable) {
        Page<Post> boards = postService.getPosts(pageable);
        return PostPageResponse.of(boards);
    }

    public PostInfoResponse getPost(Long postId) {
        Post post = postService.getPost(postId);
        return PostInfoResponse.fromEntity(post);
    }

    public CreatePostResponse createPost(Long userId, CreatePostRequest request) {
        User user = userService.getUser(userId);
        Post post = postService.createPost(request, user);
        return CreatePostResponse.fromEntity(post);
    }

    public void updatePost(Long userId, Long postId, UpdatePostRequest request) {
        User user = userService.getUser(userId);
        Post post = postService.getPost(postId);
        postService.updatePost(request, user, post);
    }

    public void deletePost(Long userId, Long postId) {
        User user = userService.getUser(userId);
        Post post = postService.getPost(postId);
        postService.deletePost(user, post);
    }
}
