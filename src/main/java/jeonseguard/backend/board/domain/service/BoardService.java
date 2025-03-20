package jeonseguard.backend.board.domain.service;

import jeonseguard.backend.board.domain.entity.Board;
import jeonseguard.backend.board.domain.factory.BoardFactory;
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
    public Page<Board> getBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long boardId) {
        return getBoardOrThrow(boardId);
    }

    @Transactional
    public Board createBoard(CreateBoardRequest request, User user) {
        Board board = BoardFactory.fromRequest(request, user);
        return boardRepository.save(board);
    }

    @Transactional
    public void updateBoard(Long boardId, UpdateBoardRequest request, User user) {
        Board board = getBoardOrThrow(boardId);
        validateAuthor(board, user, ErrorCode.BOARD_UPDATE_FORBIDDEN);
        board.updateBoard(request.newTitle(), request.newContent(), user.getNickname());
    }

    @Transactional
    public void deleteBoard(Long boardId, User user) {
        Board board = getBoardOrThrow(boardId);
        validateAuthor(board, user, ErrorCode.BOARD_DELETE_FORBIDDEN);
        boardRepository.delete(board);
    }

    private Board getBoardOrThrow(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_FOUND));
    }

    private void validateAuthor(Board board, User user, ErrorCode errorCode) {
        if (!board.getUser().getId().equals(user.getId())) {
            throw new BusinessException(errorCode);
        }
    }
}
