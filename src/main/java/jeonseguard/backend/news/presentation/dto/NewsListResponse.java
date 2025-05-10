package jeonseguard.backend.news.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.news.infrastructure.dto.response.NewsResponse;

import java.util.List;

public record NewsListResponse(
        @Schema(description = "뉴스 기사 목록")
        List<NewsResponse> newsList,

        @Schema(description = "뉴스 개수", example = "20")
        int totalCount
) {
    public static NewsListResponse from(List<NewsResponse> newsList) {
        return new NewsListResponse(newsList, newsList.size());
    }
}
