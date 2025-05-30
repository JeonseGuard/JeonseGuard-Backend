package jeonseguard.backend.auth.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record RefreshTokenVerificationResponse(
        @Schema(description = "사용자 ID") Long userId,
        @Schema(description = "리프레시 토큰") String refreshToken,
        @Schema(description = "캐시된 리프레시 토큰") String cachedRefreshToken
) {
    public static RefreshTokenVerificationResponse of(Long userId, String refreshToken, String cachedRefreshToken) {
        return new RefreshTokenVerificationResponse(
                userId,
                refreshToken,
                cachedRefreshToken
        );
    }
}
