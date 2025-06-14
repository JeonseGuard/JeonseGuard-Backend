package jeonseguard.backend.user.domain.factory;

import jeonseguard.backend.auth.infrastructure.dto.KakaoUserInfoResponse;
import jeonseguard.backend.user.domain.entity.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserFactory {
    public static User from(KakaoUserInfoResponse response) {
        return User.builder()
                .kakaoId(response.kakaoId())
                .nickname(response.getNickname())
                .profileImage(response.getProfileImage())
                .email(response.getEmail())
                .role(Role.ROLE_USER)
                .build();
    }
}
