package jeonseguard.backend.building.infrastructure.client;

import jeonseguard.backend.building.infrastructure.dto.external.BuildingRegisterOverviewItem;
import jeonseguard.backend.global.dto.OpenApiResponse;
import jeonseguard.backend.global.exception.error.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BuildingRegisterOverviewClient {
    private final WebClient webClient;

    public List<BuildingRegisterOverviewItem> fetchBuildingRegisterOverviews(URI uri) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<OpenApiResponse<BuildingRegisterOverviewItem>>() {})
                .blockOptional()
                .map(OpenApiResponse::getItems)
                .orElseThrow(() -> new BusinessException(ErrorCode.BUILDING_REGISTER_OVERVIEW_FETCH_ERROR));
    }
}
