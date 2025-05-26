package jeonseguard.backend.transaction.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record TransactionJeonseAddressRequest(
        @Schema(description = "주소")
        String address,

        @Schema(description = "번")
        String bun,

        @Schema(description = "지")
        String ji,

        @Schema(description = "층번호")
        String floorNumber
) {
        public String toCacheKey() {
                return address + ":" + bun + ":" + ji + ":" + floorNumber;
        }
}
