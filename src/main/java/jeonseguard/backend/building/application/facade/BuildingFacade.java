package jeonseguard.backend.building.application.facade;

import jeonseguard.backend.building.application.mapper.BuildingRegisterRequestMapper;
import jeonseguard.backend.building.application.service.BuildingRegisterService;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.building.presentation.dto.request.BuildingAddressRequest;
import jeonseguard.backend.building.presentation.dto.response.BuildingRegisterResponse;
import jeonseguard.backend.region.application.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static jeonseguard.backend.global.util.AddressUtil.extractRegionCode;

@Component
@RequiredArgsConstructor
public class BuildingFacade {
    private final BuildingRegisterService buildingRegisterService;
    private final RegionService regionService;

    public BuildingRegisterResponse getBuildingRegister(BuildingAddressRequest addressRequest) {
        String address = addressRequest.address();
        String regionCode = regionService.getRegionCode(address);
        String parsedRegionCode = extractRegionCode(regionCode);
        String sigunguCode = regionService.getSigunguCode(address);
        BuildingRegisterRequest request = BuildingRegisterRequestMapper.map(parsedRegionCode, sigunguCode, addressRequest);
        var overviewItem = buildingRegisterService.getBuildingRegisterOverview(1, request);
        var floorItem = buildingRegisterService.getBuildingRegisterFloor(1, request);
        var areaItem = buildingRegisterService.getBuildingRegisterArea(1, request);
        return BuildingRegisterResponse.of(overviewItem, floorItem, areaItem);
    }
}
