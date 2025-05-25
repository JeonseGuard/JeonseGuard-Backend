package jeonseguard.backend.building.application.facade;

import jeonseguard.backend.building.application.mapper.BuildingRegisterRequestMapper;
import jeonseguard.backend.building.application.service.BuildingRegisterService;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.building.presentation.dto.request.BuildingAddressRequest;
import jeonseguard.backend.building.presentation.dto.response.BuildingRegisterResponse;
import jeonseguard.backend.region.application.RegionService;
import jeonseguard.backend.region.infrastructure.dto.RegionSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import static jeonseguard.backend.global.util.AddressUtil.*;

@Component
@RequiredArgsConstructor
public class BuildingFacade {
    private final BuildingRegisterService buildingRegisterService;
    private final RegionService regionService;

    @Cacheable(
            value = "buildingRegister",
            key = "'buildingRegister::address:' + "
                    + "#addressRequest.address + ':' + "
                    + "#addressRequest.bun + ':' + "
                    + "(#addressRequest.ji != null ? #addressRequest.ji : '') + ':' + "
                    + "(#addressRequest.dongName != null ? #addressRequest.dongName : '') + ':' + "
                    + "(#addressRequest.floorName != null ? #addressRequest.floorName : '')"
    )
    public BuildingRegisterResponse getBuildingRegister(BuildingAddressRequest addressRequest) {
        RegionSummary response = regionService.getRegionSummary(addressRequest.address());
        String parsedRegionCode = extractRegionCode(response.regionCode());
        String sigunguCode = response.sigunguCode();
        BuildingRegisterRequest request = BuildingRegisterRequestMapper.convertToBuildingRegisterRequest(parsedRegionCode, sigunguCode, addressRequest);
        var overviewItem = buildingRegisterService.getBuildingRegisterOverview(1, request);
        var floorItem = buildingRegisterService.getBuildingRegisterFloor(1, request);
        var areaItem = buildingRegisterService.getBuildingRegisterArea(1, request);
        return BuildingRegisterResponse.of(overviewItem, floorItem, areaItem);
    }
}
