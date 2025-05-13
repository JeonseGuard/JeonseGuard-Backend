package jeonseguard.backend.news.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record NaverNewsSearchResponse(
        @Schema(description = "네이버 뉴스 목록")
        @JsonProperty("items")
        List<NaverNewsItem> items
) {
}
