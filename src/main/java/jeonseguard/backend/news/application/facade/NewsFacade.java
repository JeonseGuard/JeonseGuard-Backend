package jeonseguard.backend.news.application.facade;

import jeonseguard.backend.news.application.service.NewsService;
import jeonseguard.backend.news.infrastructure.dto.response.NewsResponse;
import jeonseguard.backend.news.presentation.dto.NewsListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsFacade {
    private final NewsService newsService;

    public NewsListResponse getNewsList() {
        List<NewsResponse> newsList = newsService.getNewsList();
        return NewsListResponse.from(newsList);
    }
}
