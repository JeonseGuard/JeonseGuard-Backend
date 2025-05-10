package jeonseguard.backend.news.presentation.dto;

public record NewsResponse(
        String title,
        String link,
        String publishedAt,
        String source,
        String description,
        String imageUrl
) {
}
