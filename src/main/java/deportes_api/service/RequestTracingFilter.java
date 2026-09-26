package deportes_api.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestTracingFilter extends OncePerRequestFilter {

    private static final Logger logger =
            LoggerFactory.getLogger(RequestTracingFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        long inicio = System.currentTimeMillis();

        try {

            filterChain.doFilter(request, response);

        } finally {

            long tiempo = System.currentTimeMillis() - inicio;

            logger.info(
                    "REQUEST {} {} -> {} ({} ms)",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    tiempo
            );
        }
    }
}