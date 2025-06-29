package jeonseguard.backend.user.domain.entity;

import jakarta.persistence.*;
import jeonseguard.backend.global.entity.CommonBaseEntity;
import lombok.*;

import static org.springframework.util.StringUtils.hasText;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends CommonBaseEntity {
    @Column(unique = true, nullable = false)
    private Long kakaoId;

    @Column(nullable = false)
    private String nickname;

    private String profileImage;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public void updateNickname(String nickname) {
        if (hasText(nickname)) {
            this.nickname = nickname;
        }
    }

    public void updateProfileImage(String profileImage) {
        if (hasText(profileImage)) {
            this.profileImage = profileImage;
        }
    }

    public boolean isAdmin() {
        return this.role == Role.ROLE_ADMIN;
    }
}
