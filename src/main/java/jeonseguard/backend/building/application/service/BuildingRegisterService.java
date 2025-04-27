package jeonseguard.backend.building.application.service;

import jeonseguard.backend.building.domain.BuildingRegisterApiRepository;
import jeonseguard.backend.building.infrastructure.dto.external.*;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.global.exception.error.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static jeonseguard.backend.global.util.StringUtil.*;
import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
public class BuildingRegisterService {
    private final BuildingRegisterApiRepository buildingRegisterApiRepository;

    @Transactional(readOnly = true)
    public BuildingRegisterOverviewItem getBuildingRegisterOverview(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterOverviewItem> items = fetchBuildingRegisterOverview(pageNumber, request);
        return items.stream()
                .filter(item -> !hasText(request.dongNumber()) || isSameText(request.dongNumber(), item.dongNumber()))
                .findFirst()
                .orElseGet(() -> getBuildingRegisterOverview(pageNumber + 1, request));
    }


    @Transactional(readOnly = true)
    public BuildingRegisterFloorItem getBuildingRegisterFloor(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterFloorItem> items = fetchBuildingRegisterFloor(pageNumber, request);
        return items.stream()
                .filter(item -> !hasText(request.dongNumber()) || isSameText(request.dongNumber(), item.dongNumber()))
                .filter(item -> isSameText(request.floorName(), item.floorName()))
                .findFirst()
                .orElseGet(() -> getBuildingRegisterFloor(pageNumber + 1, request));
    }

    @Transactional(readOnly = true)
    public BuildingRegisterAreaItem getBuildingRegisterArea(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterAreaItem> items = fetchBuildingRegisterArea(pageNumber, request);
        return items.stream()
                .filter(item -> "전유".equals(item.useType()))
                .filter(item -> isOptionalSameText(request.dongNumber(), item.dongNumber()))
                .filter(item -> isOptionalSameText(request.hoName(), item.hoName()))
                .findFirst()
                .orElseGet(() -> getBuildingRegisterArea(pageNumber + 1, request));
    }

    private List<BuildingRegisterOverviewItem> fetchBuildingRegisterOverview(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterOverviewItem> items = buildingRegisterApiRepository.fetchBuildingRegisterOverview(String.valueOf(pageNumber), request);
        if (items.isEmpty()) {
            throw new BusinessException(ErrorCode.BUILDING_REGISTER_OVERVIEW_FETCH_ERROR);
        }
        return items;
    }

    private List<BuildingRegisterFloorItem> fetchBuildingRegisterFloor(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterFloorItem> items = buildingRegisterApiRepository.fetchBuildingRegisterFloor(String.valueOf(pageNumber), request);
        if (items.isEmpty()) {
            throw new BusinessException(ErrorCode.BUILDING_REGISTER_FLOOR_FETCH_ERROR);
        }
        return items;
    }

    private List<BuildingRegisterAreaItem> fetchBuildingRegisterArea(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterAreaItem> items = buildingRegisterApiRepository.fetchBuildingRegisterArea(String.valueOf(pageNumber), request);
        if (items.isEmpty()) {
            throw new BusinessException(ErrorCode.BUILDING_REGISTER_AREA_FETCH_ERROR);
        }
        return items;
    }
}
