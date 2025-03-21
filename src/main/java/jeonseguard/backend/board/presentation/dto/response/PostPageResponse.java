package jeonseguard.backend.board.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.board.domain.entity.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public record BoardPageResponse(
        @Schema(description = "게시글 목록") List<BoardResponse> boards,
        @Schema(description = "현재 페이지 번호") int pageNumber,
        @Schema(description = "총 페이지 수") int totalPages,
        @Schema(description = "총 게시글 수") long totalElements
) {
    public static BoardPageResponse of(Page<Post> boards) {
        return new BoardPageResponse(
                boards.map(BoardResponse::fromEntity).getContent(),
                boards.getNumber(),
                boards.getTotalPages(),
                boards.getTotalElements()
        );
    }
}
