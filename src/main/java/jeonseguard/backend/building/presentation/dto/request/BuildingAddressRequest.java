package jeonseguard.backend.building.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BuildingAddressRequest(
        @Schema(description = "주소")
        @NotBlank(message = "주소를 입력해 주세요.")
        @Size(min = 10, max = 25, message = "주소는 10자 이상 25자 이하로 입력해 주세요.")
        String address,

        @Schema(description = "번")
        @NotBlank(message = "번을 입력해 주세요.")
        String bun,

        @Schema(description = "지")
        String ji,

        @Schema(description = "동명칭")
        String dongName,

        @Schema(description = "층명칭")
        String floorName,

        @Schema(description = "호명칭")
        String hoName
) {
}
