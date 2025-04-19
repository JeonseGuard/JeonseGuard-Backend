package jeonseguard.backend.auth.infrastructure.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record KakaoProfile(
        @Schema(description = "카카오 닉네임")
        @JsonProperty("nickname")
        String nickName,

        @Schema(description = "카카오 프로필 이미지")
        @JsonProperty("profile_image_url")
        String profileImage
) {
}
