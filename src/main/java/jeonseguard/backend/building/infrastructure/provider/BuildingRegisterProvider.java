package jeonseguard.backend.building.infrastructure.provider;

import jeonseguard.backend.building.infrastructure.client.BuildingRegisterClient;
import jeonseguard.backend.building.infrastructure.dto.external.*;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.global.config.properties.BuildingRegisterProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static jeonseguard.backend.global.util.StringUtil.*;

@Component
@RequiredArgsConstructor
public class BuildingRegisterProvider {
    private final BuildingRegisterProperties buildingRegisterProperties;
    private final BuildingRegisterClient buildingRegisterClient;

    public List<BuildingRegisterOverviewItem> getBuildingRegisterOverviews(String pageNumber, BuildingRegisterRequest request) {
        URI uri = buildUri(buildingRegisterProperties.overviewUri(), pageNumber, request.dongName(), request.hoName(), request);
        return buildingRegisterClient.fetchBuildingRegisterOverviews(uri);
    }

    public List<BuildingRegisterFloorItem> getBuildingRegisterFloors(String pageNumber, BuildingRegisterRequest request) {
        URI uri = buildUri(buildingRegisterProperties.floorUri(), pageNumber, request.dongName(), request.hoName(), request);
        return buildingRegisterClient.fetchBuildingRegisterFloors(uri);
    }

    public List<BuildingRegisterAreaItem> getBuildingRegisterAreas(String pageNumber, BuildingRegisterRequest request) {
        URI uri = buildUri(buildingRegisterProperties.areaUri(), pageNumber, request.dongName(), request.hoName(), request);
        return buildingRegisterClient.fetchBuildingRegisterAreas(uri);
    }

    public List<BuildingRegisterAreaItem> getBuildingRegisterAreasWithDongNumber(String pageNumber, BuildingRegisterRequest request) {
        URI uri = buildUri(buildingRegisterProperties.areaUri(), pageNumber, request.dongNumber(), request.hoName(), request);
        return buildingRegisterClient.fetchBuildingRegisterAreas(uri);
    }

    public List<BuildingRegisterAreaItem> getBuildingRegisterAreasWithHoNumber(String pageNumber, BuildingRegisterRequest request) {
        URI uri = buildUri(buildingRegisterProperties.areaUri(), pageNumber, request.dongName(), request.hoNumber(), request);
        return buildingRegisterClient.fetchBuildingRegisterAreas(uri);
    }

    public List<BuildingRegisterAreaItem> getBuildingRegisterAreasWithDongNumberAndHoNumber(String pageNumber, BuildingRegisterRequest request) {
        URI uri = buildUri(buildingRegisterProperties.areaUri(), pageNumber, request.dongNumber(), request.hoNumber(), request);
        return buildingRegisterClient.fetchBuildingRegisterAreas(uri);
    }

    private URI buildUri(String uri, String pageNumber, String dongValue, String hoValue, BuildingRegisterRequest request) {
        return UriComponentsBuilder.fromUriString(uri)
                .queryParam("serviceKey", encode(buildingRegisterProperties.serviceKey()))
                .queryParam("sigunguCd", request.sigunguCode())
                .queryParam("bjdongCd", request.regionCode())
                .queryParam("platGbCd", buildingRegisterProperties.categoryCode())
                .queryParam("bun", request.bun())
                .queryParam("ji", request.ji())
                .queryParam("_type", "json")
                .queryParam("numOfRows", buildingRegisterProperties.listSize())
                .queryParam("pageNo", pageNumber)
                .queryParamIfPresent("dongNm", encodeIfNotBlank(dongValue))
                .queryParamIfPresent("hoNm", encodeIfNotBlank(hoValue))
                .build(true)
                .toUri();
    }
}
