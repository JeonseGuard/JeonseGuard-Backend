package jeonseguard.backend.building.infrastructure.repository;

import jeonseguard.backend.building.domain.BuildingRegisterApiRepository;
import jeonseguard.backend.building.infrastructure.dto.external.*;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.global.config.properties.BuildingProperties;
import jeonseguard.backend.global.dto.OpenApiResponse;
import jeonseguard.backend.global.exception.error.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class BuildingRegisterApiRepositoryImpl implements BuildingRegisterApiRepository {
    private final WebClient webClient;
    private final BuildingProperties buildingProperties;

    @Override
    public List<BuildingRegisterOverviewItem> fetchBuildingRegisterOverview(String pageNumber, BuildingRegisterRequest request) {
        return webClient.get()
                .uri(buildUri(buildingProperties.overviewUri(), pageNumber, request))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<OpenApiResponse<BuildingRegisterOverviewItem>>() {})
                .blockOptional()
                .map(OpenApiResponse::getItems)
                .orElseThrow(() -> new BusinessException(ErrorCode.BUILDING_REGISTER_OVERVIEW_FETCH_ERROR));
    }

    @Override
    public List<BuildingRegisterFloorItem> fetchBuildingRegisterFloor(String pageNumber, BuildingRegisterRequest request) {
        return webClient.get()
                .uri(buildUri(buildingProperties.floorUri(), pageNumber, request))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<OpenApiResponse<BuildingRegisterFloorItem>>() {})
                .blockOptional()
                .map(OpenApiResponse::getItems)
                .orElseThrow(() -> new BusinessException(ErrorCode.BUILDING_REGISTER_FLOOR_FETCH_ERROR));
    }

    @Override
    public List<BuildingRegisterAreaItem> fetchBuildingRegisterArea(String pageNumber, BuildingRegisterRequest request) {
        return webClient.get()
                .uri(buildUri(buildingProperties.areaUri(), pageNumber, request))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<OpenApiResponse<BuildingRegisterAreaItem>>() {})
                .blockOptional()
                .map(OpenApiResponse::getItems)
                .orElseThrow(() -> new BusinessException(ErrorCode.BUILDING_REGISTER_AREA_FETCH_ERROR));
    }

    private URI buildUri(String uri, String pageNumber, BuildingRegisterRequest request) {
        return UriComponentsBuilder.fromUriString(uri)
                .queryParam("serviceKey", buildingProperties.serviceKey())
                .queryParam("sigunguCd", request.sigunguCode())
                .queryParam("bjdongCd", request.regionCode())
                .queryParam("platGbCd", buildingProperties.categoryCode())
                .queryParam("bun", request.bun())
                .queryParam("ji", request.ji())
                .queryParam("_type", "json")
                .queryParam("numOfRows", buildingProperties.listSize())
                .queryParam("pageNo", pageNumber)
                .queryParamIfPresent("dongNm", Optional.ofNullable(request.dongName()).filter(name -> !name.isBlank()))
                .queryParamIfPresent("hoNm", Optional.ofNullable(request.hoName()).filter(name -> !name.isBlank()))
                .build(true)
                .toUri();
    }
}
