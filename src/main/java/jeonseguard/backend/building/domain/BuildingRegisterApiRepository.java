package jeonseguard.backend.building.domain;

import jeonseguard.backend.building.infrastructure.dto.external.*;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;

public interface BuildingRegisterApiRepository {
    BuildingRegisterOverviewItem fetchBuildingRegisterOverview(BuildingRegisterRequest request);
    BuildingRegisterFloorItem fetchBuildingRegisterFloor(BuildingRegisterRequest request);
}
