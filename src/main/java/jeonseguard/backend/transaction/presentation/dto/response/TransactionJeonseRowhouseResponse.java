package jeonseguard.backend.transaction.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.transaction.domain.entity.TransactionJeonseRowhouse;

public record TransactionJeonseRowhouseResponse(
        @Schema(description = "계약년월")
        String contractYearMonth,

        @Schema(description = "보증금")
        String price,

        @Schema(description = "거래유형")
        String rentType,

        @Schema(description = "주택유형")
        String housingType
) {
    public static TransactionJeonseRowhouseResponse fromEntity(TransactionJeonseRowhouse rowhouse) {
        return new TransactionJeonseRowhouseResponse(
                rowhouse.getContractYearMonth(),
                rowhouse.getPrice(),
                rowhouse.getRentType(),
                rowhouse.getHousingType()
        );
    }
}
