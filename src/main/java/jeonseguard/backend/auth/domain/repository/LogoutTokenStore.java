package jeonseguard.backend.auth.domain.repository;

public interface LogoutTokenStore {
    void blacklistToken(String accessToken, long accessTokenExpirationTime);
    boolean checkBlacklistedToken(String accessToken);
}
