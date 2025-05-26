package jeonseguard.backend.building.application.facade;

import jeonseguard.backend.building.application.service.*;
import jeonseguard.backend.building.infrastructure.dto.external.*;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.building.presentation.dto.request.BuildingAddressRequest;
import jeonseguard.backend.building.presentation.dto.response.BuildingRegisterResponse;
import jeonseguard.backend.region.application.RegionQueryService;
import jeonseguard.backend.region.infrastructure.dto.RegionSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import static jeonseguard.backend.global.constant.CacheKey.BUILDING_REGISTER_PREFIX;
import static jeonseguard.backend.global.util.AddressUtil.*;

@Component
@RequiredArgsConstructor
public class BuildingFacade {
    private final BuildingRegisterOverviewQueryService buildingRegisterOverviewQueryService;
    private final BuildingRegisterFloorQueryService buildingRegisterFloorQueryService;
    private final BuildingRegisterAreaQueryService buildingRegisterAreaQueryService;
    private final RegionQueryService regionQueryService;

    @Cacheable(value = "buildingRegister", key = "'" + BUILDING_REGISTER_PREFIX + "' + #addressRequest.toCacheKey()")
    public BuildingRegisterResponse getBuildingRegister(BuildingAddressRequest addressRequest) {
        RegionSummary response = regionQueryService.getRegionSummary(addressRequest.address());
        String parsedRegionCode = extractRegionCode(response.regionCode());
        String sigunguCode = response.sigunguCode();
        BuildingRegisterRequest request = convertToBuildingRegisterRequest(parsedRegionCode, sigunguCode, addressRequest);
        BuildingRegisterOverviewItem overviewItem = buildingRegisterOverviewQueryService.getBuildingRegisterOverview(1, request);
        BuildingRegisterFloorItem floorItem = buildingRegisterFloorQueryService.getBuildingRegisterFloor(1, request);
        BuildingRegisterAreaItem areaItem = buildingRegisterAreaQueryService.getBuildingRegisterArea(1, request);
        return BuildingRegisterResponse.of(overviewItem, floorItem, areaItem);
    }

    private BuildingRegisterRequest convertToBuildingRegisterRequest(String regionCode, String sigunguCode, BuildingAddressRequest addressRequest) {
        String bun = formatBunji(addressRequest.bun());
        String ji = addressRequest.ji() != null ? formatBunji(addressRequest.ji()) : null;
        String dongNumber = addressRequest.dongName() != null ? formatDongName(addressRequest.dongName()) : null;
        String dongName = addressRequest.dongName() != null ? addressRequest.dongName() : null;
        String floorNumber = addressRequest.floorName() != null ? formatFloorName(addressRequest.floorName()) : null;
        String floorName = addressRequest.floorName() != null ? addressRequest.floorName() : null;
        String hoNumber = addressRequest.hoName() != null ? formatHoName(addressRequest.hoName()) : null;
        String hoName = addressRequest.hoName() != null ? addressRequest.hoName() : null;
        return BuildingRegisterRequest.of(regionCode, sigunguCode, bun, ji, dongNumber, dongName, floorNumber, floorName, hoNumber, hoName);
    }
}
