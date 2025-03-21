package jeonseguard.backend.user.domain.repository;

import jeonseguard.backend.user.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByKakaoId(Long kakaoId);
    Optional<User> findById(Long id);
    User save(User user);
}
