package jeonseguard.backend.transaction.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.transaction.domain.entity.TransactionSaleOfficetel;

public record TransactionSaleOfficetelResponse(
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
    public static TransactionSaleOfficetelResponse fromEntity(TransactionSaleOfficetel officetel) {
        return new TransactionSaleOfficetelResponse(
                officetel.getContractYearMonth(),
                officetel.getPrice(),
                officetel.getArea(),
                "매매",
                officetel.getHousingType()
        );
    }
}
