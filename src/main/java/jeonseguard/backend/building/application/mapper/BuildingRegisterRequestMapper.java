package jeonseguard.backend.building.application.mapper;

import jeonseguard.backend.building.infrastructure.dto.request.BuildingRegisterRequest;
import jeonseguard.backend.building.presentation.dto.request.BuildingAddressRequest;
import lombok.*;

import static jeonseguard.backend.global.util.AddressUtil.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuildingRegisterRequestMapper {
    public static BuildingRegisterRequest convertToBuildingRegisterRequest(String regionCode, String sigunguCode, BuildingAddressRequest addressRequest) {
        String bun = formatBunji(addressRequest.bun());
        String ji = addressRequest.ji() != null ? formatBunji(addressRequest.ji()) : null;
        String dongNumber = addressRequest.dongName() != null ? formatDongName(addressRequest.dongName()) : null;
        String dongName = addressRequest.dongName() != null ? addressRequest.dongName() : null;
        String floorNumber = addressRequest.floorName() != null ? formatFloorName(addressRequest.floorName()) : null;
        String floorName = addressRequest.floorName() != null ? addressRequest.floorName() : null;
        String hoNumber = addressRequest.hoName() != null ? formatHoName(addressRequest.hoName()) : null;
        String hoName = addressRequest.hoName() != null ? addressRequest.hoName() : null;
        return BuildingRegisterRequest.of(regionCode, sigunguCode, bun, ji, dongNumber, dongName, floorNumber, floorName, hoNumber, hoName);
    }
}
