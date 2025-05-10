package jeonseguard.backend.news.infrastructure.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record NaverNewsItem(
        @Schema(description = "제목")
        @JsonProperty("title")
        String title,

        @Schema(description = "링크")
        @JsonProperty("originallink")
        String link,

        @Schema(description = "요약")
        @JsonProperty("description")
        String description,

        @Schema(description = "발행일")
        @JsonProperty("pubDate")
        String publishedAt
) {
}
