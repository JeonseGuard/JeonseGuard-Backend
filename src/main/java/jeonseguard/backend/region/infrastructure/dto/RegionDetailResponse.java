package jeonseguard.backend.region.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.region.domain.entity.Region;

public record RegionDetailResponse(
        @Schema(description = "법정동코드") String regionCode,
        @Schema(description = "시군구코드") String sigunguCode
) {
    public static RegionDetailResponse fromEntity(Region region) {
        return new RegionDetailResponse(
                region.getRegionCode(),
                region.getSigunguCode()
        );
    }
}
