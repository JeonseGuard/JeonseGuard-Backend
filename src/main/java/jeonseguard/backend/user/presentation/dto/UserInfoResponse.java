package jeonseguard.backend.user.presentation.dto;

import jeonseguard.backend.user.domain.entity.User;

public record UserInfoResponse(
        Long userId,
        String nickname,
        String profileImage,
        String email,
        String role
) {
    public static UserInfoResponse fromEntity(User user) {
        return new UserInfoResponse(
                user.getId(),
                user.getNickname(),
                user.getProfileImage(),
                user.getEmail(),
                user.getRole().toString()
        );
    }
}
