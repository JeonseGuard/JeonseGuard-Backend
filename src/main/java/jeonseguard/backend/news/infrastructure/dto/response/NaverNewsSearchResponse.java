package jeonseguard.backend.news.infrastructure.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.news.infrastructure.dto.external.NaverNewsItem;

import java.util.List;

public record NaverNewsSearchResponse(
        @Schema(description = "뉴스 기사 목록")
        @JsonProperty("items")
        List<NaverNewsItem> items
) {
}
