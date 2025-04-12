package jeonseguard.backend.region.domain.entity;

import jakarta.persistence.*;
import jeonseguard.backend.global.entity.CommonBaseEntity;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region extends CommonBaseEntity {
    @Column(nullable = false, unique = true)
    private String regionCode;

    @Column(nullable = false)
    private String sigunguCode;

    @Column(nullable = false)
    private String address;
}
