package jeonseguard.backend.auth.domain;

public interface LogoutTokenStore {
    void blacklistToken(String accessToken, long accessTokenExpirationTime);
    boolean checkBlacklistedToken(String accessToken);
}
