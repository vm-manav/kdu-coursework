package com.kdu.smarthome.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Custom JWT authorization filter for processing JWT tokens in HTTP requests.
 * Extends OncePerRequestFilter for a single execution per request.
 */

@Component
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper mapper;

    /**
     * Constructs a JwtAuthorizationFilter with the provided JwtUtil and ObjectMapper.
     *
     * @param jwtUtil The JwtUtil for handling JWT operations.
     * @param mapper  The ObjectMapper for JSON processing.
     */

    public JwtAuthorizationFilter(JwtUtil jwtUtil, ObjectMapper mapper) {
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }

    /**
     * Performs the JWT token validation and authentication process for each incoming HTTP request.
     *
     * @param request     The HttpServletRequest object.
     * @param response    The HttpServletResponse object.
     * @param filterChain The FilterChain to continue the request/response chain.
     * @throws ServletException If an error occurs during servlet processing.
     * @throws IOException      If an I/O error occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, Object> errorDetails = new HashMap<>();

        try {
            String accessToken = jwtUtil.resolveToken(request);

            if (accessToken == null && !request.getRequestURI().equals("/api/v1/auth/register")) {
                log.info(request.getServletPath());
                errorDetails.put("message", "No authentication token provided");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                mapper.writeValue(response.getWriter(), errorDetails);
                return;
            }

            if(request.getServletPath().equals("/register")) {
                filterChain.doFilter(request, response);
                return;
            }
            if (accessToken != null) {
                Claims claims = jwtUtil.resolveClaims(request);

                if (claims != null && jwtUtil.validateClaims(claims)) {
                    String username = claims.getSubject();
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, "", new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            errorDetails.put("message", "Authentication Error");
            errorDetails.put("details", e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            mapper.writeValue(response.getWriter(), errorDetails);
        }

        filterChain.doFilter(request, response);
    }
}