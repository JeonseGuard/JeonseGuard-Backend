package jeonseguard.backend.heart.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record HeartResponse(
        @Schema(description = "좋아요 수") long heartCount,
        @Schema(description = "좋아요 상태") boolean heartStatus
) {
    public static HeartResponse of(long heartCount, boolean heartStatus) {
        return new HeartResponse(heartCount, heartStatus);
    }
}
