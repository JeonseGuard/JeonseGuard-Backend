package jeonseguard.backend.news.presentation.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public record NewsListResponse(
        @Schema(description = "뉴스 기사 목록")
        List<NewsResponse> newsList
) {
    public static NewsListResponse from(List<NewsResponse> newsList) {
        return new NewsListResponse(newsList);
    }
}
