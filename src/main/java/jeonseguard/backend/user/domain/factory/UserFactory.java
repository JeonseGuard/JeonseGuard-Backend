package jeonseguard.backend.user.domain.factory;

import jeonseguard.backend.auth.presentation.dto.response.KakaoUserInfoResponse;
import jeonseguard.backend.user.domain.entity.*;

public class UserFactory {
    public static User fromResponse(KakaoUserInfoResponse response) {
        return User.builder()
                .kakaoId(response.kakaoId())
                .nickname(response.getNickname())
                .profileImage(response.getProfileImage())
                .email(response.email())
                .role(Role.ROLE_USER)
                .build();
    }
}
