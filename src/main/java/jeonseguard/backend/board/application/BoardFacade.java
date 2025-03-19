package jeonseguard.backend.board.application;

import jeonseguard.backend.board.domain.entity.Board;
import jeonseguard.backend.board.domain.service.BoardService;
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
    private final BoardService boardService;
    private final UserService userService;

    public BoardPageResponse getBoards(Pageable pageable) {
        Page<Board> boards = boardService.getBoards(pageable);
        return BoardPageResponse.of(boards);
    }

    public BoardInfoResponse getBoard(Long boardId) {
        Board board = boardService.getBoard(boardId);
        return BoardInfoResponse.fromEntity(board);
    }

    public CreateBoardResponse createBoard(Long userId, CreateBoardRequest request) {
        User user = userService.getUserOrThrow(userId);
        Board board = boardService.createBoard(request, user);
        return CreateBoardResponse.fromEntity(board);
    }

    public void updateBoard(Long boardId, Long userId, UpdateBoardRequest request) {
        User user = userService.getUserOrThrow(userId);
        Board board = boardService.getBoard(boardId);
        boardService.updateBoard(boardId, request, user);
    }

    public void deleteBoard(Long boardId, Long userId) {
        User user = userService.getUserOrThrow(userId);
        boardService.deleteBoard(boardId, user);
    }
}
