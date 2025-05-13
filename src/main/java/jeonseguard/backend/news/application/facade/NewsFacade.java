package jeonseguard.backend.news.application.facade;

import jeonseguard.backend.news.application.service.NewsService;
import jeonseguard.backend.news.presentation.dto.NewsListResponse;
import jeonseguard.backend.news.presentation.dto.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsFacade {
    private final NewsService newsService;

    @Cacheable(value = "newsList", unless = "#result == null")
    public NewsListResponse getNewsList() {
        List<NewsResponse> newsList = newsService.getNewsList();
        return NewsListResponse.from(newsList);
    }
}
