package jeonseguard.backend.building.application.service;

import jeonseguard.backend.building.infrastructure.dto.external.BuildingRegisterFloorItem;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.building.infrastructure.provider.BuildingRegisterProvider;
import jeonseguard.backend.global.exception.error.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static jeonseguard.backend.global.util.StringUtil.isSameText;
import static org.springframework.util.StringUtils.hasText;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BuildingRegisterFloorQueryService {
    private final BuildingRegisterProvider buildingRegisterProvider;

    public BuildingRegisterFloorItem getBuildingRegisterFloor(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterFloorItem> items = fetchBuildingRegisterFloor(pageNumber, request);
        return items.stream()
                .filter(item -> !hasText(request.dongName()) || isSameText(request.dongName(), item.dongName()) || isSameText(request.dongNumber(), item.dongName()))
                .filter(item -> isSameText(request.floorName(), item.floorName()) || isSameText(request.floorNumber(), item.floorName()))
                .findFirst()
                .orElseGet(() -> getBuildingRegisterFloor(pageNumber + 1, request));
    }

    private List<BuildingRegisterFloorItem> fetchBuildingRegisterFloor(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterFloorItem> items = buildingRegisterProvider.getBuildingRegisterFloors(String.valueOf(pageNumber), request);
        if (items.isEmpty()) {
            throw new BusinessException(ErrorCode.BUILDING_REGISTER_FLOOR_FETCH_ERROR);
        }
        return items;
    }
}
