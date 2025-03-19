package jeonseguard.backend.board.domain.service;

import jeonseguard.backend.board.domain.entity.Board;
import jeonseguard.backend.board.domain.factory.BoardFactory;
import jeonseguard.backend.board.infrastructure.BoardRepository;
import jeonseguard.backend.board.presentation.dto.request.CreateBoardRequest;
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

    public Page<Board> getBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Board getBoardOrThrow(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException(ErrorCode.BOARD_NOT_FOUND));
    }

    @Transactional
    public Board createBoard(CreateBoardRequest request, User user) {
        Board board = BoardFactory.fromRequest(request, user);
        return boardRepository.save(board);
    }
}
