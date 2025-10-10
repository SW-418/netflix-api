package samwells.io.netflix_api.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "app.jwt")
public class JwtConfig {
    private String secret;
    private String issuer;
    private String audience;
    private long expirationInMs;
}
