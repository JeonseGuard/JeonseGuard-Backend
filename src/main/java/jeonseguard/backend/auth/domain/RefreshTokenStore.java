package jeonseguard.backend.auth.domain;

public interface RefreshTokenStore {
    void saveRefreshToken(Long userId, String refreshToken, long refreshExpirationTime);
    String getRefreshToken(Long userId);
    void removeRefreshToken(Long userId);
}
