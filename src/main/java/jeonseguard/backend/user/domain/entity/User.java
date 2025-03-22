package jeonseguard.backend.user.domain.entity;

import jakarta.persistence.*;
import jeonseguard.backend.board.domain.entity.Post;
import jeonseguard.backend.global.entity.CommonBaseEntity;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Post> posts;
}
