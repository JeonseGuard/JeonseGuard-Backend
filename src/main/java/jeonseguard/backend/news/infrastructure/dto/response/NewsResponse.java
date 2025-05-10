package jeonseguard.backend.news.infrastructure.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record NewsResponse(
        @Schema(description = "제목")
        String title,

        @Schema(description = "링크")
        String link,

        @Schema(description = "요약")
        String description,

        @Schema(description = "발행일")
        String publishedAt
) {
    public static NewsResponse of(String title, String link, String description, String publishedAt) {
        return new NewsResponse(
                title,
                link,
                description,
                publishedAt
        );
    }
}
