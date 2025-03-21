package jeonseguard.backend.board.domain.service;

import jeonseguard.backend.board.domain.entity.Post;
import jeonseguard.backend.board.domain.factory.PostFactory;
import jeonseguard.backend.board.infrastructure.BoardRepository;
import jeonseguard.backend.board.presentation.dto.request.*;
import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Page<Post> getBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Post getBoard(Long boardId) {
        return getBoardOrThrow(boardId);
    }

    @Transactional
    public Post createBoard(CreateBoardRequest request, User user) {
        Post post = PostFactory.fromRequest(request, user);
        return boardRepository.save(post);
    }

    @Transactional
    public void updateBoard(Long boardId, UpdateBoardRequest request, User user) {
        Post post = getBoardOrThrow(boardId);
        validateAuthor(post, user, ErrorCode.BOARD_UPDATE_FORBIDDEN);
        post.updateBoard(request.newTitle(), request.newContent(), user.getNickname());
    }

    @Transactional
    public void deleteBoard(Long boardId, User user) {
        Post post = getBoardOrThrow(boardId);
        validateAuthor(post, user, ErrorCode.BOARD_DELETE_FORBIDDEN);
        boardRepository.delete(post);
    }

    private Post getBoardOrThrow(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_FOUND));
    }

    private void validateAuthor(Post post, User user, ErrorCode errorCode) {
        if (!post.getUser().getId().equals(user.getId())) {
            throw new BusinessException(errorCode);
        }
    }
}
