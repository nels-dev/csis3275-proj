package csis3275.project.seasell.configuration;

import csis3275.project.seasell.user.repository.UserRepository;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * This class serves as the main security configuration using Spring Security DSL. Includes
 *
 * <pre>
 * 1. A custom filter that intercepts "/api/login", validate credentials and return a JWT token
 * 2. Configures the application as a OAuth2 resource server that accepts JWT Authentication
 * 3. Configures protected path patterns (endpoints) and their required role
 * 4. Other miscellaneous config such as disabling some default security features
 * </pre>
 *
 * See <a href="https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html">OAuth 2.0
 * Resource Server JWT</a>
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JWTAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider) throws Exception {
        http
                // Allow cross-origin requests
                .cors().and()
                // Disable CSRF (it has no use since we are not storing cookies)
                .csrf().disable()
                // disable x-frame-options. This is essential for h2-console
                .headers().frameOptions().disable().and()
                // Stateless session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(jwtAuthenticationFilter).authorizeHttpRequests()
                // Public endpoints always permitted
                .requestMatchers("/", "/api/login", "/api/register", "/hello", "/images/**").permitAll()
                // Essential for h2-console
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                // Configure Client-Facing endpoints
                .requestMatchers("/api/client/**").hasRole("CLIENT")
                // Configure Admin-Facing endpoints
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                // Fallback, protect all other requests
                .anyRequest().authenticated().and().authenticationProvider(authenticationProvider).formLogin().disable()
                .httpBasic().disable().oauth2ResourceServer().jwt();
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }

    // Password Encoder is used to encode password when saving into database or comparing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Implementation of UserDetailsService that loads UserDetails given a username
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // Configure a bean of AuthenticationProvider which uses our own PasswordEncoder and UserDetailsService
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
