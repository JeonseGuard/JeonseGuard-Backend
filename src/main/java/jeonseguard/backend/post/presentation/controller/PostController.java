package jeonseguard.backend.post.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jeonseguard.backend.global.annotation.AuthenticatedUser;
import jeonseguard.backend.post.application.facade.PostFacade;
import jeonseguard.backend.post.presentation.dto.request.*;
import jeonseguard.backend.post.presentation.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Post", description = "게시글 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v5/post")
public class PostController {
    private final PostFacade postFacade;
//
//    @Operation(summary = "카테고리별 게시글 전체 조회", description = "카테고리별 게시글을 페이지네이션 방식으로 전체 조회합니다.")
//    @GetMapping("/{category}/page")
//    public ResponseEntity<PostPageResponse> getPostPageByCategory(@PathVariable String category, Pageable pageable) {
//        return ResponseEntity.ok(postFacade.getPostPageByCategory(category, pageable));
//    }

    @Operation(summary = "게시글 상세 조회", description = "게시글 ID를 이용하여, 게시글을 상세 조회합니다.")
    @GetMapping("/{postId}")
    public ResponseEntity<PostInfoResponse> getPostInfo(@AuthenticatedUser Long userId, @PathVariable Long postId) {
        return ResponseEntity.ok(postFacade.getPostInfo(userId, postId));
    }

    @Operation(summary = "카테고리별 게시글 생성", description = "카테고리별 게시글을 생성합니다.")
    @PostMapping("/{category}")
    public ResponseEntity<CreatePostResponse> createPostByCategory(@AuthenticatedUser Long userId, @PathVariable String category, @Valid @RequestBody CreatePostRequest request) {
        return ResponseEntity.ok(postFacade.createPostByCategory(userId, category, request));
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @PatchMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@AuthenticatedUser Long userId, @PathVariable Long postId, @RequestBody UpdatePostRequest request) {
        postFacade.updatePost(userId, postId, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@AuthenticatedUser Long userId, @PathVariable Long postId) {
        postFacade.deletePost(userId, postId);
        return ResponseEntity.noContent().build();
    }
}
