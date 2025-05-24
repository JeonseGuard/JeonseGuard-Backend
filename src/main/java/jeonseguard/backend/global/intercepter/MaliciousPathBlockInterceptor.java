package jeonseguard.backend.global.intercepter;

import jakarta.servlet.http.*;
import jeonseguard.backend.global.config.properties.MaliciousPathProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class MaliciousPathBlockInterceptor implements HandlerInterceptor {
    private final MaliciousPathProperties properties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        String path = request.getRequestURI();
        return isNotBlockedPath(path) || handleBlockedPath(response);
    }

    private boolean isNotBlockedPath(String path) {
        return properties.blockedPathPrefixes().stream()
                .noneMatch(path::startsWith);
    }

    private boolean handleBlockedPath(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return false;
    }
}
