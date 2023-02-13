package csis3275.project.seasell.configuration;

import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@ConfigurationProperties(prefix = "jwt")
@Configuration
@Data
public class JWTConfiguration {
    private String secret;

    public SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    // A custom jwtDecoder which trust a single symmetric key configured in the application
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(getSecretKey()).macAlgorithm(MacAlgorithm.HS256).build();
    }

    // By default, when Jwt is converted to authentication object, "scope" field in JWT is mapped to authority with
    // "SCOPE_" prefix
    // This customization overrides that behaviour and use "ROLE_" prefix
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}
