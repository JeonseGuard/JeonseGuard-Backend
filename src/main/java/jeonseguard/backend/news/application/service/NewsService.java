package jeonseguard.backend.news.application.service;

import jeonseguard.backend.news.application.mapper.NewsResponseMapper;
import jeonseguard.backend.news.infrastructure.dto.*;
import jeonseguard.backend.news.infrastructure.provider.NaverSearchProvider;
import jeonseguard.backend.news.presentation.dto.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsService {
    private final NaverSearchProvider searchProvider;

    @Transactional(readOnly = true)
    public List<NewsResponse> getNewsList() {
        NaverNewsSearchResponse response = searchProvider.fetchNews();
        return response.items().stream()
                .map(NewsResponseMapper::from)
                .toList();
    }
}
