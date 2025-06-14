package jeonseguard.backend.building.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.building.infrastructure.dto.external.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public record BuildingRegisterResponse(
        @Schema(description = "도로명주소")
        String newAddress,

        @Schema(description = "대지면적 (m^2)")
        String landArea,

        @Schema(description = "건축면적 (m^2)")
        String buildingArea,

        @Schema(description = "건물 구조")
        String buildingStructure,

        @Schema(description = "건물 용도")
        String buildingPurpose,

        @Schema(description = "층별 구조")
        String floorStructure,

        @Schema(description = "층별 용도")
        String floorPurpose,

        @Schema(description = "층별 상세 용도")
        String floorDetailPurpose,

        @Schema(description = "전유면적 (m^2)")
        String exclusiveArea
) {
        public static BuildingRegisterResponse of(BuildingRegisterOverviewItem overviewItem, BuildingRegisterFloorItem floorItem, BuildingRegisterAreaItem areaItem) {
                return new BuildingRegisterResponse(
                        overviewItem.newAddress(),
                        overviewItem.landArea(),
                        overviewItem.buildingArea(),
                        overviewItem.buildingStructure(),
                        overviewItem.buildingPurpose(),
                        floorItem.floorStructure(),
                        floorItem.floorPurpose(),
                        floorItem.floorDetailPurpose(),
                        areaItem.area()
                );
        }
}
