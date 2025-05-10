package jeonseguard.backend.news.application.mapper;

import jeonseguard.backend.news.infrastructure.dto.external.NaverNewsItem;
import jeonseguard.backend.news.infrastructure.dto.response.NewsResponse;
import lombok.*;
import org.jsoup.Jsoup;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NewsResponseMapper {
    public static NewsResponse toResponse(NaverNewsItem item) {
        String title = Jsoup.parse(item.title()).text();
        String description = Jsoup.parse(item.description()).text();
        return NewsResponse.of(title, item.link(), description, item.publishedAt());
    }
}
