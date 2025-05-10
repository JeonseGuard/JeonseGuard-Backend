package jeonseguard.backend.news.application.service;

import jeonseguard.backend.news.application.mapper.NewsResponseMapper;
import jeonseguard.backend.news.infrastructure.provider.NaverNewsProvider;
import jeonseguard.backend.news.infrastructure.dto.response.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsService {
    private final NaverNewsProvider newsProvider;

    @Cacheable(value = "newsList", unless = "#result == null or #result.isEmpty()")
    @Transactional(readOnly = true)
    public List<NewsResponse> getNewsList() {
        return newsProvider.getNaverNewsItems().stream()
                .map(NewsResponseMapper::toResponse)
                .toList();
    }
}
