package br.com.market.users.user.service;

import br.com.market.users.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${sprint-evaluation-4.jwt.expiration}")
    private Long expiration;

    @Value("${sprint-evaluation-4.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Date date = new Date();

        long expirationDate = date.getTime() + expiration;

        Date expiration = new Date(expirationDate);

        return Jwts.builder()
                .setIssuer("Users API")
                .setSubject(user.getId().toString())
                .setIssuedAt(date)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean isValidToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);

            return true;
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
}
