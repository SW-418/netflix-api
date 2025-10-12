package samwells.io.netflix_api.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {
    public static Long getUserId() {
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
