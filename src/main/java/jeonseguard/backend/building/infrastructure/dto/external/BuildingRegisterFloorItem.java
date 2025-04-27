package jeonseguard.backend.building.infrastructure.dto.external;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BuildingRegisterFloorItem(
        @Schema(description = "동번호")
        @JsonProperty("dongNm")
        String dongNumber,

        @Schema(description = "층명칭")
        @JsonProperty("flrNoNm")
        String floorName,

        @Schema(description = "층별 구조")
        @JsonProperty("strctCdNm")
        String floorStructure,

        @Schema(description = "층별 용도")
        @JsonProperty("mainPurpsCdNm")
        String floorPurpose,

        @Schema(description = "층별 상세 용도")
        @JsonProperty("etcPurps")
        String floorDetailPurpose
) {
}
