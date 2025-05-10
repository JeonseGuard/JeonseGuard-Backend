package jeonseguard.backend.news.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jeonseguard.backend.news.application.facade.NewsFacade;
import jeonseguard.backend.news.presentation.dto.NewsListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "News", description = "뉴스 기사 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v5/news")
public class NewsController {
    private final NewsFacade newsFacade;

    @Operation(summary = "뉴스 기사 목록 조회", description = "뉴스 기사 목록을 조회합니다.")
    @GetMapping()
    public ResponseEntity<NewsListResponse> getNewsList() {
        return ResponseEntity.ok(newsFacade.getNewsList());
    }
}
