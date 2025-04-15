package jeonseguard.backend.region.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record DeleteRegionRequest(
        @Schema(description = "주소")
        @NotBlank(message = "주소를 입력해 주세요.")
        String address
) {
}
