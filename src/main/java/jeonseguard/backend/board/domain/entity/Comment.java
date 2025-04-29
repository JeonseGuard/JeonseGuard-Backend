package jeonseguard.backend.board.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jeonseguard.backend.global.entity.CommonBaseEntity;
import jeonseguard.backend.user.domain.entity.User;
import lombok.*;

import static org.springframework.util.StringUtils.hasText;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends CommonBaseEntity {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, updatable = false)
    private String createdBy;

    @Column(nullable = false)
    private String updatedBy;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public void updateComment(String content, String updatedBy) {
        if (hasText(content)) {
            this.content = content;
        }
        this.updatedBy = updatedBy;
    }
}
