package jeonseguard.backend.board.domain.entity;

import jakarta.persistence.*;
import jeonseguard.backend.global.entity.CommonBaseEntity;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Heart extends CommonBaseEntity {
    @Column(nullable = false)
    private Long targetId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HeartTarget target;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
