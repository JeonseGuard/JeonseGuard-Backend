package jeonseguard.backend.region.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.region.domain.entity.Region;

public record RegionResponse(
        @Schema(description = "법정동코드")
        String regionCode,

        @Schema(description = "시군구코드")
        String sigunguCode
) {
    public static RegionResponse fromEntity(Region region) {
        return new RegionResponse(
                region.getRegionCode(),
                region.getSigunguCode()
        );
    }
}
