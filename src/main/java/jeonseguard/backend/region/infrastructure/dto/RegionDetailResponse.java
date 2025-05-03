package jeonseguard.backend.region.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.region.domain.entity.Region;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
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
