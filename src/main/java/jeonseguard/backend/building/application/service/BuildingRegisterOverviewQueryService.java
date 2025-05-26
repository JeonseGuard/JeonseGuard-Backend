package jeonseguard.backend.building.application.service;

import jeonseguard.backend.building.infrastructure.dto.external.BuildingRegisterOverviewItem;
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
public class BuildingRegisterOverviewQueryService {
    private final BuildingRegisterProvider buildingRegisterProvider;

    public BuildingRegisterOverviewItem getBuildingRegisterOverview(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterOverviewItem> items = fetchBuildingRegisterOverview(pageNumber, request);
        return items.stream()
                .filter(item -> !hasText(request.dongName()) || isSameText(request.dongName(), item.dongName()) || isSameText(request.dongNumber(), item.dongName()))
                .findFirst()
                .orElseGet(() -> getBuildingRegisterOverview(pageNumber + 1, request));
    }

    private List<BuildingRegisterOverviewItem> fetchBuildingRegisterOverview(int pageNumber, BuildingRegisterRequest request) {
        List<BuildingRegisterOverviewItem> items = buildingRegisterProvider.getBuildingRegisterOverviews(String.valueOf(pageNumber), request);
        if (items.isEmpty()) {
            throw new BusinessException(ErrorCode.BUILDING_REGISTER_OVERVIEW_FETCH_ERROR);
        }
        return items;
    }
}
