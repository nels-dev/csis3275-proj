package csis3275.project.seasell.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import csis3275.project.seasell.user.model.LoginRequest;
import csis3275.project.seasell.user.model.LoginResponse;
import csis3275.project.seasell.user.model.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Date;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    JWTConfiguration jwtConfiguration;
    ObjectMapper objectMapper;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTConfiguration jwtConfiguration,
            ObjectMapper objectMapper) {
        setFilterProcessesUrl("/api/login");
        setAuthenticationManager(authenticationManager);
        this.jwtConfiguration = jwtConfiguration;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            LoginRequest loginDTO = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            return this.getAuthenticationManager()
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read login payload", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) {
        AppUser user = (AppUser) authResult.getPrincipal();
        try {
            Claims claims = Jwts.claims().setSubject(user.getEmail());
            claims.put("scope", user.getRole().name());
            String token = Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE).setIssuedAt(new Date())
                    .setClaims(claims).setExpiration(Date.from(OffsetDateTime.now().plusDays(1).toInstant()))
                    .signWith(SignatureAlgorithm.HS256, jwtConfiguration.getSecretKey()).compact();
            LoginResponse loginResponse = LoginResponse.builder().user(user).token(token).build();
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            objectMapper.writeValue(response.getOutputStream(), loginResponse);
        } catch (Exception e) {
            logger.error("Unable to write login response", e);
            throw new RuntimeException("Unable to write login response", e);
        }
    }
}
