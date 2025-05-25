package jeonseguard.backend.news.application.service;

import jeonseguard.backend.news.infrastructure.dto.NaverNews;
import jeonseguard.backend.news.infrastructure.provider.NaverSearchProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class NewsQueryService {
    private final NaverSearchProvider searchProvider;

    @Cacheable(value = "news", unless = "#result == null")
    public List<NaverNews> getNews() {
        return searchProvider.getNews().items();
    }
}
