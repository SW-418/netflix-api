package samwells.io.netflix_api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            long startTime = Instant.now().toEpochMilli();
            String requestId = UUID.randomUUID().toString();
            String inbound = String.format("%s %s", request.getMethod(), request.getRequestURI());
            MDC.put("requestId", requestId);
            MDC.put("userId", SecurityContextHolder.getContext().getAuthentication().getName());
            log.info(inbound);

            filterChain.doFilter(request, response);

            String outbound = String.format("%s %s - status=%s - time=%sms", request.getMethod(), request.getRequestURI(), response.getStatus(), Instant.now().toEpochMilli() - startTime);
            log.info(outbound);
        } finally {
            MDC.clear();
        }
    }
}
