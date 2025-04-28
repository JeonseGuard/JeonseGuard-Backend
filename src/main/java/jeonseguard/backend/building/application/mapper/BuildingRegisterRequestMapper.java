package jeonseguard.backend.building.application.mapper;

import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.building.presentation.dto.request.BuildingAddressRequest;
import lombok.*;

import static jeonseguard.backend.global.util.AddressUtil.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuildingRegisterRequestMapper {
    public static BuildingRegisterRequest map(String regionCode, String sigunguCode, BuildingAddressRequest addressRequest) {
        String bun = formatBunji(addressRequest.bun());
        String ji = addressRequest.ji() != null ? formatBunji(addressRequest.ji()) : null;
        String dongName = addressRequest.dongName() != null ? formatDongName(addressRequest.dongName()) : null;
        String floorName = addressRequest.floorName() != null ? addressRequest.floorName() : null;
        String hoName = addressRequest.hoName() != null ? addressRequest.hoName() : null;
        return BuildingRegisterRequest.of(regionCode, sigunguCode, bun, ji, dongName, floorName, hoName);
    }
}
