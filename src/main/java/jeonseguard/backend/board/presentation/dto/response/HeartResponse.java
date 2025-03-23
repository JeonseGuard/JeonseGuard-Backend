package jeonseguard.backend.board.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record HeartResponse(
        @Schema(description = "좋아요 수") long count,
        @Schema(description = "좋아요 상태") boolean status
) {
    public static HeartResponse of(long count, boolean status) {
        return new HeartResponse(count, status);
    }
}
