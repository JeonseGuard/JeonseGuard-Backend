package jeonseguard.backend.jeonse.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record JeonseTransactionRowhouseResponse(
        @Schema(description = "전월세 구분")
        String rentType,

        @Schema(description = "계약년월")
        String contractYearMonth,

        @Schema(description = "보증금")
        String price,

        @Schema(description = "주택유형")
        String housingType
) {
}
