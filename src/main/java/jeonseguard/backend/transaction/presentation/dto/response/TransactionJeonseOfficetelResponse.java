package jeonseguard.backend.transaction.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.transaction.domain.entity.TransactionJeonseOfficetel;

public record TransactionJeonseOfficetelResponse(
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
    public static TransactionJeonseOfficetelResponse from(TransactionJeonseOfficetel officetel) {
        return new TransactionJeonseOfficetelResponse(
                officetel.getContractYearMonth(),
                officetel.getPrice(),
                officetel.getArea(),
                "전세",
                officetel.getHousingType()
        );
    }
}
