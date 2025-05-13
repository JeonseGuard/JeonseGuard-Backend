package jeonseguard.backend.news.infrastructure.provider;

import jeonseguard.backend.global.config.properties.NaverSearchProperties;
import jeonseguard.backend.news.infrastructure.client.NaverSearchClient;
import jeonseguard.backend.news.infrastructure.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class NaverSearchProvider {
    private final NaverSearchClient naverSearchClient;
    private final NaverSearchProperties properties;

    public NaverNewsSearchResponse fetchNews() {
        URI uri = buildUri(properties.newsEndpoint(), properties.newsQuery(), properties.newsDisplay(), properties.newsSort());
        return naverSearchClient.fetchNews(uri, properties.clientId(), properties.clientSecret());
    }

    private URI buildUri(String endpoint, String query, String display, String sort) {
        return UriComponentsBuilder.fromUriString(endpoint)
                .queryParam("query", query)
                .queryParam("display", display)
                .queryParam("sort", sort)
                .encode()
                .build()
                .toUri();
    }
}
