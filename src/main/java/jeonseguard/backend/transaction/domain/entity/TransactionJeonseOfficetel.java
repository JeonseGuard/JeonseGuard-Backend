package jeonseguard.backend.transaction.domain.entity;

import jakarta.persistence.*;
import jeonseguard.backend.global.entity.CommonBaseEntity;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionJeonseOfficetel extends CommonBaseEntity {
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String bun;

    @Column(nullable = false)
    private String ji;

    @Column(nullable = false)
    private String floor;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String contractYearMonth;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String housingType;
}
