package br.com.market.users.security.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDetailServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AuthenticationUser("admin",
                "$2a$12$LlxKRPHgE2I41V9o2hcN5ud.4dRUA67QCZhDUbh9C6GevJBoyoPo2",
                true,
                true,
                true,
                true,
                new ArrayList<>(),
                null);
    }

    private AuthenticationUser getAuthenticationUser(String username) {
        AuthenticationUser authenticationUser = jdbcTemplate.queryForObject(
                "SELECT email, password, userId FROM users WHERE email=?", new Object[]{username},
                new UserRowMapper()
        );

        if (authenticationUser != null) {
            authenticationUser = new AuthenticationUser(authenticationUser.getUsername(), authenticationUser.getPassword(), authenticationUser.isEnabled(),
                    authenticationUser.isAccountNonExpired(), authenticationUser.isCredentialsNonExpired(),
                    authenticationUser.isAccountNonLocked(), getUserRoles(authenticationUser), authenticationUser.getUserId());
        }

        return authenticationUser;
    }

    private static class UserRowMapper implements RowMapper<AuthenticationUser> {
        @Override
        public AuthenticationUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new AuthenticationUser(rs.getString("email"), rs.getString("password"), true, true, true, true,
                    Collections.emptyList(), rs.getLong("userId"));
        }
    }

    private List<GrantedAuthority> getUserRoles(AuthenticationUser user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        return authorities;
    }
}
