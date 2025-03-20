package jeonseguard.backend.board.domain.factory;

import jeonseguard.backend.board.domain.entity.Board;
import jeonseguard.backend.board.presentation.dto.request.CreateBoardRequest;
import jeonseguard.backend.user.domain.entity.User;

public class BoardFactory {
    public static Board fromRequest(CreateBoardRequest request, User user) {
        return Board.builder()
                .title(request.title())
                .content(request.content())
                .user(user)
                .createdBy(user.getNickname())
                .updatedBy(user.getNickname())
                .build();
    }
}
