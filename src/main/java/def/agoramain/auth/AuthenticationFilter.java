package def.agoramain.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

    private final JwtUtil jwtUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필요한 경우 초기화 작업 수행
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;


        String requestURI = httpRequest.getRequestURI();

        log.info("[REQUEST] {}", requestURI);

        /**
         * 안정화 전까지 검증x 안정화 이후 G/W로 인증 기능 이관
         */

//        if (requestURI.startsWith("/swagger-ui") || requestURI.startsWith("/api-docs")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
//
//        String token = httpRequest.getHeader("Authorization");
//
//        if (!jwtUtil.isValidate(token)) {
//            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰이 유효하지 않습니다.");
//            return;
//        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // 필요한 경우 리소스 해제 작업 수행
    }
}
