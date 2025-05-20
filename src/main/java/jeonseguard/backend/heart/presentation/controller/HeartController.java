package jeonseguard.backend.heart.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jeonseguard.backend.global.annotation.AuthenticatedUser;
import jeonseguard.backend.heart.application.facade.HeartFacade;
import jeonseguard.backend.heart.presentation.dto.request.ToggleHeartRequest;
import jeonseguard.backend.heart.presentation.dto.response.HeartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Heart", description = "좋아요 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v5/heart")
public class HeartController {
    private final HeartFacade heartFacade;

    @Operation(summary = "게시글 좋아요", description = "게시글에 좋아요를 누르거나 취소합니다.")
    @PostMapping()
    public ResponseEntity<HeartResponse> togglePostHeart(@AuthenticatedUser Long userId, @Valid @RequestBody ToggleHeartRequest request) {
        return ResponseEntity.ok(heartFacade.togglePostHeart(userId, request));
    }
}
