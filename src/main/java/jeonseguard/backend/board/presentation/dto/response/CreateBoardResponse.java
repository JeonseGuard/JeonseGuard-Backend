package jeonseguard.backend.board.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.board.domain.entity.Board;

public record CreateBoardResponse(
        @Schema(description = "게시글 ID", example = "100") Long boardId
) {
    public static CreateBoardResponse fromEntity(Board board) {
        return new CreateBoardResponse(
                board.getId()
        );
    }
}
