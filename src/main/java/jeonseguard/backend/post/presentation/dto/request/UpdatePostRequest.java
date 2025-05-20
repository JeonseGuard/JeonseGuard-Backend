package jeonseguard.backend.post.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdatePostRequest(
        @Schema(description = "새로운 게시글 제목")
        String newTitle,

        @Schema(description = "새로운 게시글 내용")
        String newContent
) {
}
