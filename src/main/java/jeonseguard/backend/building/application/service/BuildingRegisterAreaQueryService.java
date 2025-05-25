package jeonseguard.backend.building.application.service;

import jeonseguard.backend.building.infrastructure.dto.external.BuildingRegisterAreaItem;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.building.infrastructure.provider.BuildingRegisterProvider;
import jeonseguard.backend.global.exception.error.BusinessException;
import jeonseguard.backend.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

import static jeonseguard.backend.global.util.StringUtil.isOptionalSameText;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BuildingRegisterAreaQueryService {
    private final BuildingRegisterProvider buildingRegisterProvider;

    public BuildingRegisterAreaItem getBuildingRegisterArea(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterAreaItem> items = fetchBuildingRegisterArea(pageNumber, request);
        return items.stream()
                .filter(item -> "전유".equals(item.useType()))
                .filter(item -> isOptionalSameText(request.dongName(), item.dongName()) || isOptionalSameText(request.dongNumber(), item.dongName()))
                .filter(item -> isOptionalSameText(request.hoName(), item.hoName()) || isOptionalSameText(request.hoNumber(), item.hoName()))
                .findFirst()
                .orElseGet(() -> getBuildingRegisterArea(pageNumber + 1, request));
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
