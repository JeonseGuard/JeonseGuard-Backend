package jeonseguard.backend.news.application.mapper;

import jeonseguard.backend.news.infrastructure.dto.*;
import jeonseguard.backend.news.presentation.dto.NewsResponse;
import lombok.*;
import org.jsoup.Jsoup;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NewsResponseMapper {
    public static NewsResponse from(NaverNewsItem item) {
        return NewsResponse.of(
                Jsoup.parse(item.title()).text(),
                item.link(),
                Jsoup.parse(item.description()).text(),
                item.publishedAt()
        );
    }
}
