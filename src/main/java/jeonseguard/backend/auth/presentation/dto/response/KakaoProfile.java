package jeonseguard.backend.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoProfile(
        @JsonProperty("nickname") String nickName,
        @JsonProperty("profile_image_url") String profileImage
) {
}
