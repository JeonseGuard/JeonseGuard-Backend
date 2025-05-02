package jeonseguard.backend.board.domain.entity;

import jakarta.persistence.*;
import jeonseguard.backend.global.entity.CommonBaseEntity;
import lombok.*;

import static org.springframework.util.StringUtils.hasText;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends CommonBaseEntity {
    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, updatable = false)
    private String createdBy;

    @Column(nullable = false)
    private String updatedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardCategory category;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public void updatePost(String title, String content, String updatedBy) {
        if (hasText(title)) {
            this.title = title;
        }
        if (hasText(content)) {
            this.content = content;
        }
        this.updatedBy = updatedBy;
    }
}
