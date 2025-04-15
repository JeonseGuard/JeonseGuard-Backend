package jeonseguard.backend.region.application.facade;

import jeonseguard.backend.global.exception.error.ErrorCode;
import jeonseguard.backend.region.application.service.RegionService;
import jeonseguard.backend.region.presentation.dto.DeleteRegionRequest;
import jeonseguard.backend.user.application.service.UserService;
import jeonseguard.backend.user.domain.entity.User;
import jeonseguard.backend.user.domain.policy.UserPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionFacade {
    private final RegionService regionService;
    private final UserService userService;

    public void deleteRegion(Long adminId, DeleteRegionRequest request) {
        User user = userService.getUser(adminId);
        UserPolicy.validateAdmin(user, ErrorCode.REGION_DELETE_FORBIDDEN);
        regionService.deleteRegion(request);
    }
}
