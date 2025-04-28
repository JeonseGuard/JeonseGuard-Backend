package jeonseguard.backend.building.application.facade;

import jeonseguard.backend.building.application.service.BuildingRegisterService;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.building.presentation.dto.request.BuildingAddressRequest;
import jeonseguard.backend.building.presentation.dto.response.BuildingRegisterResponse;
import jeonseguard.backend.region.application.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static jeonseguard.backend.global.util.AddressUtil.*;

@Component
@RequiredArgsConstructor
public class BuildingFacade {
    private final BuildingRegisterService buildingRegisterService;
    private final RegionService regionService;

    public BuildingRegisterResponse getBuildingRegister(BuildingAddressRequest addressRequest) {
        BuildingRegisterRequest request = createBuildingRegisterRequest(addressRequest);
        var overviewItem = buildingRegisterService.getBuildingRegisterOverview(1, request);
        var floorItem = buildingRegisterService.getBuildingRegisterFloor(1, request);
        var areaItem = buildingRegisterService.getBuildingRegisterArea(1, request);
        return BuildingRegisterResponse.of(overviewItem, floorItem, areaItem);
    }

    private BuildingRegisterRequest createBuildingRegisterRequest(BuildingAddressRequest addressRequest) {
        String regionCode = regionService.getRegionCode(addressRequest.address());
        String parsedRegionCode = extractRegionCode(regionCode);
        String sigunguCode = regionService.getSigunguCode(addressRequest.address());
        String bun = formatBunji(addressRequest.bun());
        String ji = addressRequest.ji() != null ? formatBunji(addressRequest.ji()) : null;
        String dongName = addressRequest.dongName() != null ? formatDongName(addressRequest.dongName()) : null;
        String floorName = addressRequest.floorName() != null ? addressRequest.floorName() : null;
        String hoName = addressRequest.hoName() != null ? addressRequest.hoName() : null;
        return BuildingRegisterRequest.of(parsedRegionCode, sigunguCode, bun, ji, dongName, floorName, hoName);
    }
}
