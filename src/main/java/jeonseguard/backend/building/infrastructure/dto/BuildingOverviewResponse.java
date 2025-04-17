package jeonseguard.backend.building.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record BuildingOverviewResponse(
        @Schema(description = "대지면적 (m^2)")
        @JsonProperty("platArea")
        String landArea,

        @Schema(description = "건축면적 (m^2)")
        @JsonProperty("archArea")
        String buildingArea,

        @Schema(description = "건물 구조")
        @JsonProperty("strctCdNm")
        String buildingStructure,

        @Schema(description = "건물 용도")
        @JsonProperty("mainPurpsCdNm")
        String buildingPurpose
) {
}
