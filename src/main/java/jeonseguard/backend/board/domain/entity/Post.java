package jeonseguard.backend.board.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jeonseguard.backend.global.entity.CommonBaseEntity;
import jeonseguard.backend.user.domain.entity.User;
import lombok.*;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> comments;

    public void updatePost(String title, String content, String updatedBy) {
        updatePostTitle(title);
        updatePostContent(content);
        this.updatedBy = updatedBy;
    }

    private void updatePostTitle(String title) {
        if (title != null && !title.isBlank()) {
            this.title = title;
        }
    }

    private void updatePostContent(String content) {
        if (content != null && !content.isBlank()) {
            this.content = content;
        }
    }
}
