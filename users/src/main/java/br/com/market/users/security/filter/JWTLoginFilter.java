package br.com.market.users.security.filter;

import br.com.market.users.security.component.AuthenticationUser;
import br.com.market.users.security.component.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        br.com.market.users.security.model.Authentication credentials =
                new br.com.market.users.security.model.Authentication();
        credentials.setLogin(login);
        credentials.setPassword(password);

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                credentials.getLogin(), credentials.getPassword(), Collections.emptyList()
        ));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AuthenticationUser user = (AuthenticationUser) authResult.getPrincipal();

        br.com.market.users.security.model.Authentication authentication =
                TokenAuthenticationService.getToken(response, authResult.getName(), authResult.getAuthorities(), Math.toIntExact(user.getUserId()));

        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.writeValueAsString(authentication);

        response.addHeader("Content-Type", "application/json");
        response.getWriter().println(message);
    }
}
