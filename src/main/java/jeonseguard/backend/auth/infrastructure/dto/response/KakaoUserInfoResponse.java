package jeonseguard.backend.auth.infrastructure.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jeonseguard.backend.auth.infrastructure.dto.external.KakaoAccount;

public record KakaoUserInfoResponse(
        @Schema(description = "카카오 ID")
        @JsonProperty("id")
        Long kakaoId,

        @Schema(description = "카카오 계정")
        @JsonProperty("kakao_account")
        KakaoAccount account
) {
    public String getNickname() {
        return account.profile().nickName();
    }

    public String getProfileImage() {
        return account.profile().profileImage();
    }

    public String getEmail() {
        return account.email();
    }
}
