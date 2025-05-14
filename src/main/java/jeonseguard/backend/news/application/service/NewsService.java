package jeonseguard.backend.news.application.service;

import jeonseguard.backend.news.application.mapper.NewsResponseMapper;
import jeonseguard.backend.news.infrastructure.dto.NaverNewsSearchResponse;
import jeonseguard.backend.news.infrastructure.provider.NaverSearchProvider;
import jeonseguard.backend.news.presentation.dto.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NewsService {
    private final NaverSearchProvider searchProvider;

    @Cacheable(value = "newsList", unless = "#result == null")
    @Transactional(readOnly = true)
    public List<NewsResponse> getNewsList() {
        NaverNewsSearchResponse response = searchProvider.fetchNews();
        return response.items().stream()
                .map(NewsResponseMapper::from)
                .collect(Collectors.toList());
    }
}
