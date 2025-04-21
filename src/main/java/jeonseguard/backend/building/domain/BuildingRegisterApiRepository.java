package jeonseguard.backend.building.domain;

import jeonseguard.backend.building.infrastructure.dto.external.*;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;

public interface BuildingRegisterApiRepository {
    BuildingRegisterOverviewItem fetchBuildingRegisterOverview(String pageNumber, BuildingRegisterRequest request);
    BuildingRegisterFloorItem fetchBuildingRegisterFloor(String pageNumber, BuildingRegisterRequest request);
}
