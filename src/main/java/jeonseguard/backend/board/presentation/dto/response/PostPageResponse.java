package jeonseguard.backend.board.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

public record PostPageResponse(
        @Schema(description = "전체 페이지 수") int totalPages,
        @Schema(description = "전체 게시글 수") long totalElements,
        @Schema(description = "페이지 당 게시글 수") int pageSize,
        @Schema(description = "현재 페이지 번호") int pageNumber,
        @Schema(description = "게시글 목록") List<PostResponse> posts
) {
    public static PostPageResponse from(Page<PostResponse> page) {
        return new PostPageResponse(
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber(),
                page.getContent()
        );
    }
}
