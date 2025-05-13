package jeonseguard.backend.news.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record NewsResponse(
        @Schema(description = "뉴스 기사 제목")
        String title,

        @Schema(description = "뉴스 기사 링크")
        String link,

        @Schema(description = "뉴스 기사 요약")
        String description,

        @Schema(description = "뉴스 기사 발행일")
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
