package jeonseguard.backend.building.application.facade;

import jeonseguard.backend.building.application.service.BuildingRegisterService;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.building.presentation.dto.request.BuildingAddressRequest;
import jeonseguard.backend.building.presentation.dto.response.BuildingRegisterResponse;
import jeonseguard.backend.region.application.RegionService;
import jeonseguard.backend.region.infrastructure.dto.RegionInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static jeonseguard.backend.global.util.AddressUtil.*;

@Component
@RequiredArgsConstructor
public class BuildingFacade {
    private final BuildingRegisterService buildingRegisterService;
    private final RegionService regionService;

    public BuildingRegisterResponse getBuildingRegister(BuildingAddressRequest addressRequest) {
        BuildingRegisterRequest request = convertToBuildingRegisterRequest(addressRequest);
        var overviewItem = buildingRegisterService.getBuildingRegisterOverview(1, request);
        var floorItem = buildingRegisterService.getBuildingRegisterFloor(1, request);
        var areaItem = buildingRegisterService.getBuildingRegisterArea(1, request);
        return BuildingRegisterResponse.of(overviewItem, floorItem, areaItem);
    }

    public BuildingRegisterRequest convertToBuildingRegisterRequest(BuildingAddressRequest addressRequest) {
        String address = addressRequest.address();
        RegionInfoResponse regionInfoResponse = regionService.getRegionInfo(address);
        String parsedRegionCode = extractRegionCode(regionInfoResponse.regionCode());
        String sigunguCode = regionInfoResponse.sigunguCode();
        String bun = formatBunji(addressRequest.bun());
        String ji = addressRequest.ji() != null ? formatBunji(addressRequest.ji()) : null;
        String dongNumber = addressRequest.dongName() != null ? formatDongName(addressRequest.dongName()) : null;
        String dongName = addressRequest.dongName() != null ? addressRequest.dongName() : null;
        String floorName = addressRequest.floorName() != null ? addressRequest.floorName() : null;
        String hoName = addressRequest.hoName() != null ? addressRequest.hoName() : null;
        return BuildingRegisterRequest.of(parsedRegionCode, sigunguCode, bun, ji, dongNumber, dongName, floorName, hoName);
    }
}
