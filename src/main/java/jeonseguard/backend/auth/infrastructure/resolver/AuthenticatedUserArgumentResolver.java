package jeonseguard.backend.auth.infrastructure.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jeonseguard.backend.global.annotation.AuthenticatedUser;
import jeonseguard.backend.auth.domain.repository.LogoutTokenStore;
import jeonseguard.backend.auth.infrastructure.provider.JwtTokenProvider;
import jeonseguard.backend.global.exception.error.*;
import lombok.*;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.*;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtTokenProvider tokenProvider;
    private final LogoutTokenStore logoutTokenStore;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AuthenticatedUser.class) != null && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String token = extractToken(webRequest);
        validateBlacklistedToken(token);
        return tokenProvider.getUserIdFromToken(token);
    }

    private String extractToken(NativeWebRequest webRequest) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String header = request.getHeader("Authorization");
        return (header != null && header.startsWith("Bearer ")) ? header.substring(7) : null;
    }

    private void validateBlacklistedToken(String token) {
        if (logoutTokenStore.checkBlacklistedToken(token)) {
            throw new BusinessException(ErrorCode.TOKEN_IS_BLACKLISTED);
        }
    }
}
