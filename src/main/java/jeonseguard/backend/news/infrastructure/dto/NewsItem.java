package jeonseguard.backend.news.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NewsItem(
        @JsonProperty("title")
        String title,

        @JsonProperty("originallink")
        String link,

        @JsonProperty("description")
        String description,

        @JsonProperty("pubDate")
        String publishedAt
) {
}
