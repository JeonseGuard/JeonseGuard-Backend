package jeonseguard.backend.building.infrastructure.dto.external;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BuildingRegisterAreaItem(
        @Schema(description = "동명칭")
        @JsonProperty("dongNm")
        String dongName,

        @Schema(description = "호명칭")
        @JsonProperty("hoNm")
        String hoName,

        @Schema(description = "전유/공용 구분")
        @JsonProperty("exposPubuseGbCdNm")
        String useType,

        @Schema(description = "면적 (m^2)")
        @JsonProperty("area")
        String area
) {
}
