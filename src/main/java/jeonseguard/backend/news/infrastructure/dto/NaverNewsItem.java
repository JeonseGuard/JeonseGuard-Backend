package jeonseguard.backend.news.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record NaverNewsItem(
        @Schema(description = "네이버 뉴스 기사 제목")
        @JsonProperty("title")
        String title,

        @Schema(description = "네이버 뉴스 기사 링크")
        @JsonProperty("originallink")
        String link,

        @Schema(description = "네이버 뉴스 기사 요약")
        @JsonProperty("description")
        String description,

        @Schema(description = "네이버 뉴스 기사 발행일")
        @JsonProperty("pubDate")
        String publishedAt
) {
}
