package jeonseguard.backend.transaction.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.transaction.domain.entity.TransactionSaleRowhouse;

public record TransactionSaleRowhouseResponse(
        @Schema(description = "계약년월")
        String contractYearMonth,

        @Schema(description = "보증금")
        String price,

        @Schema(description = "전용면적(m^2)")
        String area,

        @Schema(description = "임대유형")
        String rentType,

        @Schema(description = "주택유형")
        String housingType
) {
    public static TransactionSaleRowhouseResponse from(TransactionSaleRowhouse rowhouse) {
        return new TransactionSaleRowhouseResponse(
                rowhouse.getContractYearMonth(),
                rowhouse.getPrice(),
                rowhouse.getArea(),
                "매매",
                rowhouse.getHousingType()
        );
    }
}
