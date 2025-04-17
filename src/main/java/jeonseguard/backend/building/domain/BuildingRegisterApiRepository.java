package jeonseguard.backend.building.domain;

import jeonseguard.backend.building.infrastructure.dto.BuildingOverviewResponse;
import jeonseguard.backend.building.presentation.dto.request.BuildingRegisterRequest;

public interface BuildingRegisterApiRepository {
    BuildingOverviewResponse fetchBuildingOverview(BuildingRegisterRequest request);
}
