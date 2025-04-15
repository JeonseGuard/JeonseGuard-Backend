package jeonseguard.backend.region.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jeonseguard.backend.global.annotation.AuthenticatedUser;
import jeonseguard.backend.region.application.facade.RegionFacade;
import jeonseguard.backend.region.presentation.dto.DeleteRegionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Region", description = "행정구역 정보 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/region")
public class RegionController {
    private final RegionFacade regionFacade;

    @Operation(summary = "행정구역 삭제", description = "주소를 이용하여, 특정 행정구역을 삭제합니다.")
    @DeleteMapping()
    public ResponseEntity<Void> deleteRegion(@AuthenticatedUser Long adminId, DeleteRegionRequest request) {
        regionFacade.deleteRegion(adminId, request);
        return ResponseEntity.noContent().build();
    }
}
