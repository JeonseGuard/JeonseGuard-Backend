package jeonseguard.backend.building.infrastructure.dto.external;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BuildingRegisterExclusiveAreaItem(
        @Schema(description = "전유/공용 구분")
        @JsonProperty("exposPubuseGbCdNm")
        String useType,

        @Schema(description = "전유면적 (m^2)")
        @JsonProperty("area")
        String exclusiveArea
) {
}
