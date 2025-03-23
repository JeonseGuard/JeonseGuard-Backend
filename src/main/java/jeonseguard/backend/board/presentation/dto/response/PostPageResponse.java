package jeonseguard.backend.board.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.board.domain.entity.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public record PostPageResponse(
        @Schema(description = "게시글 목록") List<PostResponse> posts,
        @Schema(description = "현재 페이지 번호") long pageNumber,
        @Schema(description = "총 페이지 수") long totalPages,
        @Schema(description = "총 게시글 수") long totalElements
) {
    public static PostPageResponse of(List<PostResponse> posts, Page<Post> pages) {
        return new PostPageResponse(
                posts,
                pages.getNumber(),
                pages.getTotalPages(),
                pages.getTotalElements()
        );
    }
}
