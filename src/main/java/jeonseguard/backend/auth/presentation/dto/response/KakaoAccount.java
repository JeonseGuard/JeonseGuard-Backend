package jeonseguard.backend.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoAccount(
        @JsonProperty("profile") KakaoProfile profile
) {
}
