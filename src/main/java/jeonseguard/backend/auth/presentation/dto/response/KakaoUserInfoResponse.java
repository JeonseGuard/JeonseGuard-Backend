package jeonseguard.backend.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoUserInfoResponse(
        @JsonProperty("id") Long kakaoId,
        @JsonProperty("kakao_account") KakaoAccount account,
        @JsonProperty("email") String email
) {
    public String getNickname() {
        return account.profile().nickName();
    }

    public String getProfileImage() {
        return account.profile().profileImage();
    }
}
