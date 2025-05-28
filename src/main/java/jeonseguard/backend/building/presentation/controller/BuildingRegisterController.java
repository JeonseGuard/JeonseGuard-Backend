package jeonseguard.backend.building.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jeonseguard.backend.building.application.facade.BuildingRegisterFacade;
import jeonseguard.backend.building.presentation.dto.request.BuildingAddressRequest;
import jeonseguard.backend.building.presentation.dto.response.BuildingRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Building Register", description = "건축물대장 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v5/building")
public class BuildingRegisterController {
    private final BuildingRegisterFacade buildingRegisterFacade;

    @Operation(summary = "건축물대장 통합 조회", description = "주소 정보를 이용하여, 건축물대장의 표제부, 층별개요, 전유면적을 통합 조회합니다.")
    @PostMapping("/register")
    public ResponseEntity<BuildingRegisterResponse> getBuildingRegister(@Valid @RequestBody BuildingAddressRequest request) {
        return ResponseEntity.ok(buildingRegisterFacade.getBuildingRegister(request));
    }
}
