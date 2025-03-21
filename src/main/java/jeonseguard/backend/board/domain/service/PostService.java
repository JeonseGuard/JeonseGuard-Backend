package jeonseguard.backend.board.domain.service;

import jeonseguard.backend.board.domain.entity.Post;
import jeonseguard.backend.board.domain.factory.PostFactory;
import jeonseguard.backend.board.domain.repository.PostRepository;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
    }

    @Transactional
    public Post createPost(CreatePostRequest request, User user) {
        Post post = PostFactory.fromRequest(request, user);
        return postRepository.save(post);
    }

    @Transactional
    public void updatePost(UpdatePostRequest request, User user, Post post) {
        validateAuthor(user, post, ErrorCode.POST_UPDATE_FORBIDDEN);
        post.updateBoard(request.newTitle(), request.newContent(), user.getNickname());
    }

    @Transactional
    public void deletePost(User user, Post post) {
        validateAuthor(user, post, ErrorCode.POST_DELETE_FORBIDDEN);
        postRepository.delete(post);
    }

    private void validateAuthor(User user, Post post, ErrorCode errorCode) {
        if (!post.getUser().getId().equals(user.getId())) {
            throw new BusinessException(errorCode);
        }
    }
}
