package jeonseguard.backend.building.domain;

import jeonseguard.backend.building.infrastructure.dto.external.*;
import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;

import java.util.List;

public interface BuildingRegisterApiRepository {
    List<BuildingRegisterOverviewItem> fetchBuildingRegisterOverview(String pageNumber, BuildingRegisterRequest request);
    List<BuildingRegisterFloorItem> fetchBuildingRegisterFloor(String pageNumber, BuildingRegisterRequest request);
    List<BuildingRegisterAreaItem> fetchBuildingRegisterArea(String pageNumber, BuildingRegisterRequest request);
    List<BuildingRegisterAreaItem> fetchBuildingRegisterAreaWithDongNumber(String pageNumber, BuildingRegisterRequest request);
    List<BuildingRegisterAreaItem> fetchBuildingRegisterAreaWithHoNumber(String pageNumber, BuildingRegisterRequest request);
    List<BuildingRegisterAreaItem> fetchBuildingRegisterAreaWithDongNumberAndHoNumber(String pageNumber, BuildingRegisterRequest request);
}
