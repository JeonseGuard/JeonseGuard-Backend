package jeonseguard.backend.board.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public record CommentResponse(
        @Schema(description = "댓글 ID") Long commentId,
        @Schema(description = "내용") String content,
        @Schema(description = "생성자") String creator,
        @Schema(description = "생성일") LocalDateTime createdDateTime
) {
    @QueryProjection
    public CommentResponse {}
}
