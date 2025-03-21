package jeonseguard.backend.board.domain.factory;

import jeonseguard.backend.board.domain.entity.Post;
import jeonseguard.backend.board.presentation.dto.request.CreateBoardRequest;
import jeonseguard.backend.user.domain.entity.User;

public class BoardFactory {
    public static Post fromRequest(CreateBoardRequest request, User user) {
        return Post.builder()
                .title(request.title())
                .content(request.content())
                .user(user)
                .createdBy(user.getNickname())
                .updatedBy(user.getNickname())
                .build();
    }
}
