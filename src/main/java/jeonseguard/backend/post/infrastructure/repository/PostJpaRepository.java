package jeonseguard.backend.post.infrastructure.repository;

import jeonseguard.backend.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
}
