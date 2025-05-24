package jeonseguard.backend.global.intercepter;

import jakarta.servlet.http.*;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MaliciousPathBlockInterceptor implements HandlerInterceptor {
    private static final String BLOCKED_PATH_PREFIX = "/goform";

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        String path = request.getRequestURI();
        return !path.startsWith(BLOCKED_PATH_PREFIX) || blockAccess(response);
    }

    private boolean blockAccess(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return false;
    }
}
