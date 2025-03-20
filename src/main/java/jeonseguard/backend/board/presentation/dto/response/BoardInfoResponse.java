package jeonseguard.backend.board.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.board.domain.entity.Board;

import java.time.LocalDate;

public record BoardInfoResponse(
        @Schema(description = "게시글 ID") Long boardId,
        @Schema(description = "제목") String title,
        @Schema(description = "내용") String content,
        @Schema(description = "생성자") String creator,
        @Schema(description = "생성일") LocalDate createdAt
) {
    public static BoardInfoResponse fromEntity(Board board) {
        return new BoardInfoResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getCreatedBy(),
                board.getCreatedAt().toLocalDate()
        );
    }
}
