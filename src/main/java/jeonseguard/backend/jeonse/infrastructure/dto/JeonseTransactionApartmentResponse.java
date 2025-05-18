package jeonseguard.backend.jeonse.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record JeonseTransactionApartmentResponse(
        @Schema(description = "전월세 구분")
        String rent_type,

        @Schema(description = "계약년월")
        String contract_year_month,

        @Schema(description = "보증금")
        String price,

        @Schema(description = "주택유형")
        String housing_type
) {
}
