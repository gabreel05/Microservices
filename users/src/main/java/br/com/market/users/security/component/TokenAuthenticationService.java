package br.com.market.users.security.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class TokenAuthenticationService {

    static final Long EXPIRATION_TIME = (100L * 365 * 24 * 60 * 60 * 1000);
    static final String SECRET = "NotTooSecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER = "Authorization";
    static final String USER_ID = "USER_ID";

    public static br.com.market.users.security.model.Authentication getToken(HttpServletResponse response, String username,
                                                                             Collection<? extends GrantedAuthority> authorities, Integer userId) {
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        br.com.market.users.security.model.Authentication authentication = new br.com.market.users.security.model.Authentication();
        authentication.setToken(token);
        authentication.setLogin(username);

        return authentication;
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER);

        if (token != null) {
            Claims claims;

            try {
                claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
            } catch (ExpiredJwtException e) {
                claims = e.getClaims();
            }

            if (claims != null) {
                if (claims.getSubject() != null) {
                    return new UsernamePasswordAuthenticationToken(claims.getSubject(), new ArrayList<>(), new ArrayList<>());
                }
            }
        }

        return null;
    }

    public static Map<String, String> getValuesFromToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER);

        if (token != null) {
            Claims claims;

            try {
                claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
            } catch (ExpiredJwtException e) {
                claims = e.getClaims();
            }

            if (claims != null) {
                HashMap<String, String> values = new HashMap<>();

                Long userId = (Long) claims.get(USER_ID);
                values.put("userId", userId == null ? null : userId.toString());

                return values;
            }
        }

        return null;
    }
}

