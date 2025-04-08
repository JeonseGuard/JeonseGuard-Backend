package jeonseguard.backend.regioncode.domain.entity;

import jakarta.persistence.*;
import jeonseguard.backend.global.entity.CommonBaseEntity;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegionCode extends CommonBaseEntity {
    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String sidoName;

    private String sigunguName;

    private String eupmyeondongName;

    private String riName;

    private Integer orderNo;

    private LocalDate createdDate;

    private LocalDate deletedDate;

    private String pastCode;
}
