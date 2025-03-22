package jeonseguard.backend.board.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jeonseguard.backend.global.entity.CommonBaseEntity;
import jeonseguard.backend.user.domain.entity.User;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonBackReference
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public void updateComment(String content, String updatedBy) {
        if (content != null && !content.isEmpty()) {
            this.content = content;
        }
        this.updatedBy = updatedBy;
    }
}
