package com.kdu.smarthome.auth;

import com.kdu.smarthome.model.entities.Users;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Utility class responsible for handling JSON Web Tokens (JWT) in the Smart Home application.
 * Manages token creation, parsing, resolving, and validation.
 */
@Component
@Slf4j
public class JwtUtil {


    private final String secret_key = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    private long accessTokenValidity = 3600000;

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    /**
     * Constructs a JwtUtil with the predefined secret key and access token validity duration.
     */
    public JwtUtil(){
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    /**
     * Creates a JWT token for the specified user.
     *
     * @param user The user for whom the token is created.
     * @return The generated JWT token.
     */
    public String createToken(Users user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("username",user.getUsername());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    /**
     * Parses JWT claims from the given token.
     *
     * @param token The JWT token to parse.
     * @return The parsed JWT claims.
     */
    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    /**
     * Resolves and parses JWT claims from the provided HttpServletRequest.
     *
     * @param req The HttpServletRequest containing the JWT token.
     * @return The parsed JWT claims.
     * @throws ExpiredJwtException      If the JWT token has expired.
     * @throws AuthenticationException If there is an authentication-related error.
     */
    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    /**
     * Resolves the JWT token from the Authorization header of the HttpServletRequest.
     *
     * @param request The HttpServletRequest containing the JWT token.
     * @return The resolved JWT token.
     */
    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    /**
     * Validates the expiration of JWT claims.
     *
     * @param claims The JWT claims to validate.
     * @return True if the claims are not expired; false otherwise.
     * @throws AuthenticationException If there is an authentication-related error.
     */
    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            log.error("Error occurred during JWT claims validation: {}", e.getMessage());
            return false;
        }
    }

}
