package jeonseguard.backend.user.infrastructure;

import jeonseguard.backend.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByKakaoId(Long kakaoId);
}
