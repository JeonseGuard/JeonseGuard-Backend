package jeonseguard.backend.jeonse.domain.entity;

import jakarta.persistence.*;
import jeonseguard.backend.global.entity.CommonBaseEntity;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JeonseTransactionRowhouse extends CommonBaseEntity {
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String bun;

    @Column(nullable = false)
    private String ji;

    @Column(nullable = false)
    private String floor;

    @Column(nullable = false)
    private String rent_type;

    @Column(nullable = false)
    private String contract_year_month;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String housing_type;
}
