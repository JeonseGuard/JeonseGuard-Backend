package jeonseguard.backend.building.infrastructure.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BuildingRegisterRequest(
        @Schema(description = "법정동코드")
        String regionCode,

        @Schema(description = "시군구코드")
        String sigunguCode,

        @Schema(description = "번")
        String bun,

        @Schema(description = "지")
        String ji,

        @Schema(description = "동번호")
        String dongNumber,

        @Schema(description = "동명칭")
        String dongName,

        @Schema(description = "층번호")
        String floorNumber,

        @Schema(description = "층명칭")
        String floorName,

        @Schema(description = "호번호")
        String hoNumber,

        @Schema(description = "호명칭")
        String hoName
) {
        public static BuildingRegisterRequest of(String regionCode, String sigunguCode, String bun, String ji, String dongNumber, String dongName, String floorNumber, String floorName, String hoNumber, String hoName) {
                return new BuildingRegisterRequest(
                        regionCode,
                        sigunguCode,
                        bun,
                        ji,
                        dongNumber,
                        dongName,
                        floorNumber,
                        floorName,
                        hoNumber,
                        hoName
                );
        }
}
