package jeonseguard.backend.transaction.presentation.dto.response;

import java.util.List;

public record TransactionSummaryOfficetelResponse(
        List<TransactionJeonseOfficetelResponse> jeonses,
        List<TransactionSaleOfficetelResponse> sales
) {
    public static TransactionSummaryOfficetelResponse of(
            List<TransactionJeonseOfficetelResponse> jeonses,
            List<TransactionSaleOfficetelResponse> sales
    ) {
        return new TransactionSummaryOfficetelResponse(jeonses, sales);
    }
}
