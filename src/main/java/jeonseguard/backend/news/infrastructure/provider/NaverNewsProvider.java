package jeonseguard.backend.news.infrastructure.provider;

import jeonseguard.backend.global.config.properties.NaverNewsProperties;
import jeonseguard.backend.news.infrastructure.client.NaverNewsClient;
import jeonseguard.backend.news.infrastructure.dto.external.NaverNewsItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NaverNewsProvider {
    private final NaverNewsClient naverNewsClient;
    private final NaverNewsProperties properties;

    public List<NaverNewsItem> getNaverNewsItems() {
        URI uri = buildUri(properties.endpoint(), properties.query(), properties.display());
        return naverNewsClient.fetchNaverNewsItems(uri, properties.clientId(), properties.clientSecret());
    }

    private URI buildUri(String endpoint, String query, String display) {
        return UriComponentsBuilder.newInstance()
                .uri(URI.create(endpoint))
                .queryParam("query", query)
                .queryParam("display", display)
                .queryParam("sort", "date")
                .build()
                .encode()
                .toUri();
    }
}
