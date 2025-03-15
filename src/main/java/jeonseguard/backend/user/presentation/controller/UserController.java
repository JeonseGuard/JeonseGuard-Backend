package jeonseguard.backend.user.presentation.controller;

import jeonseguard.backend.auth.presentation.resolver.AuthenticatedUser;
import jeonseguard.backend.user.application.UserFacade;
import jeonseguard.backend.user.presentation.dto.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserFacade userFacade;

    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> getMyInfo(@AuthenticatedUser Long userId) {
        return ResponseEntity.ok(userFacade.getUser(userId));
    }
}
