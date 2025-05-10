package jeonseguard.backend.news.infrastructure.client;

import jeonseguard.backend.global.exception.error.*;
import jeonseguard.backend.news.infrastructure.dto.external.NaverNewsItem;
import jeonseguard.backend.news.infrastructure.dto.response.NaverNewsSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NaverNewsClient {
    private final WebClient webClient;

    public List<NaverNewsItem> fetchNaverNewsItems(URI uri, String clientId, String clientSecret) {
        return webClient.get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .retrieve()
                .bodyToMono(NaverNewsSearchResponse.class)
                .blockOptional()
                .map(NaverNewsSearchResponse::items)
                .orElseThrow(() -> new BusinessException(ErrorCode.NAVER_NEWS_FETCH_FAILED));
    }
}
