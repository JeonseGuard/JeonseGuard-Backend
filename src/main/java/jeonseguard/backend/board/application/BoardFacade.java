package jeonseguard.backend.board.application;

import jeonseguard.backend.board.domain.entity.Board;
import jeonseguard.backend.board.domain.service.BoardService;
import jeonseguard.backend.board.presentation.dto.request.CreateBoardRequest;
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

    public BoardInfoResponse getBoardInfo(Long boardId) {
        Board board = boardService.getBoardOrThrow(boardId);
        return BoardInfoResponse.fromEntity(board);
    }

    public CreateBoardResponse createBoard(Long userId, CreateBoardRequest request) {
        User user = userService.getUserOrThrow(userId);
        Board board = boardService.createBoard(request, user);
        return CreateBoardResponse.fromEntity(board);
    }
}
