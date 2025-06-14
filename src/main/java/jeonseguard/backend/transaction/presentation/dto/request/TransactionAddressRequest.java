package jeonseguard.backend.transaction.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record TransactionAddressRequest(
        @Schema(description = "주소")
        @NotBlank(message = "주소를 입력해 주세요.")
        String address,

        @Schema(description = "번")
        @NotBlank(message = "번을 입력해 주세요.")
        String bun,

        @Schema(description = "지")
        @NotBlank(message = "지를 입력해 주세요.")
        String ji,

        @Schema(description = "층번호")
        @NotBlank(message = "층번호를 입력해 주세요.")
        String floorNumber,

        @Schema(description = "전유면적 (m^2)")
        @NotBlank(message = "전유면적 (m^2)을 입력해 주세요.")
        String area
) {
    public String toCacheKey() {
        return address + ":" + bun + ":" + ji + ":" + floorNumber + ":" + area;
    }
}
