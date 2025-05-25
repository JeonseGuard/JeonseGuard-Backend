package jeonseguard.backend.building.application.service;

import jeonseguard.backend.building.infrastructure.dto.external.*;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.building.infrastructure.provider.BuildingRegisterProvider;
import jeonseguard.backend.global.exception.error.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

import static jeonseguard.backend.global.util.StringUtil.*;
import static org.springframework.util.StringUtils.hasText;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BuildingRegisterService {
    private final BuildingRegisterProvider buildingRegisterProvider;

    public BuildingRegisterOverviewItem getBuildingRegisterOverview(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterOverviewItem> items = fetchBuildingRegisterOverview(pageNumber, request);
        return items.stream()
                .filter(item -> !hasText(request.dongName()) || isSameText(request.dongName(), item.dongName()) || isSameText(request.dongNumber(), item.dongName()))
                .findFirst()
                .orElseGet(() -> getBuildingRegisterOverview(pageNumber + 1, request));
    }

    public BuildingRegisterFloorItem getBuildingRegisterFloor(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterFloorItem> items = fetchBuildingRegisterFloor(pageNumber, request);
        return items.stream()
                .filter(item -> !hasText(request.dongName()) || isSameText(request.dongName(), item.dongName()) || isSameText(request.dongNumber(), item.dongName()))
                .filter(item -> isSameText(request.floorName(), item.floorName()) || isSameText(request.floorNumber(), item.floorName()))
                .findFirst()
                .orElseGet(() -> getBuildingRegisterFloor(pageNumber + 1, request));
    }

    public BuildingRegisterAreaItem getBuildingRegisterArea(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterAreaItem> items = fetchBuildingRegisterArea(pageNumber, request);
        return items.stream()
                .filter(item -> "전유".equals(item.useType()))
                .filter(item -> isOptionalSameText(request.dongName(), item.dongName()) || isOptionalSameText(request.dongNumber(), item.dongName()))
                .filter(item -> isOptionalSameText(request.hoName(), item.hoName()) || isOptionalSameText(request.hoNumber(), item.hoName()))
                .findFirst()
                .orElseGet(() -> getBuildingRegisterArea(pageNumber + 1, request));
    }

    private List<BuildingRegisterOverviewItem> fetchBuildingRegisterOverview(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterOverviewItem> items = buildingRegisterProvider.getBuildingRegisterOverviews(String.valueOf(pageNumber), request);
        if (items.isEmpty()) {
            throw new BusinessException(ErrorCode.BUILDING_REGISTER_OVERVIEW_FETCH_ERROR);
        }
        return items;
    }

    private List<BuildingRegisterFloorItem> fetchBuildingRegisterFloor(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterFloorItem> items = buildingRegisterProvider.getBuildingRegisterFloors(String.valueOf(pageNumber), request);
        if (items.isEmpty()) {
            throw new BusinessException(ErrorCode.BUILDING_REGISTER_FLOOR_FETCH_ERROR);
        }
        return items;
    }

    private List<BuildingRegisterAreaItem> fetchBuildingRegisterArea(int pageNumber, BuildingRegisterRequest request) {
        List<Supplier<List<BuildingRegisterAreaItem>>> fetchStrategies = buildAreaFetchStrategies(String.valueOf(pageNumber), request);
        return fetchStrategies.stream()
                .map(Supplier::get)
                .filter(items -> !items.isEmpty())
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.BUILDING_REGISTER_AREA_FETCH_ERROR));
    }

    private List<Supplier<List<BuildingRegisterAreaItem>>> buildAreaFetchStrategies(String pageNumber, BuildingRegisterRequest request) {
        return List.of(
                () -> buildingRegisterProvider.getBuildingRegisterAreas(pageNumber, request),
                () -> buildingRegisterProvider.getBuildingRegisterAreasWithHoNumber(pageNumber, request),
                () -> buildingRegisterProvider.getBuildingRegisterAreasWithDongNumber(pageNumber, request),
                () -> buildingRegisterProvider.getBuildingRegisterAreasWithDongNumberAndHoNumber(pageNumber, request)
        );
    }
}
