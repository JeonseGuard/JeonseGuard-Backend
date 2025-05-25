package jeonseguard.backend.news.application.facade;

import jeonseguard.backend.news.application.service.NewsService;
import jeonseguard.backend.news.infrastructure.dto.NaverNews;
import jeonseguard.backend.news.presentation.dto.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NewsFacade {
    private final NewsService newsService;

    public List<NewsResponse> getNews() {
        return newsService.getNews().stream()
                .map(this::convertToNewsResponse)
                .collect(Collectors.toList());
    }

    private NewsResponse convertToNewsResponse(NaverNews item) {
        return NewsResponse.of(
                Jsoup.parse(item.title()).text(),
                item.link(),
                Jsoup.parse(item.description()).text(),
                item.publishedAt()
        );
    }
}
