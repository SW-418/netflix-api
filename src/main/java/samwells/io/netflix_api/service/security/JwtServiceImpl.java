package samwells.io.netflix_api.service.security;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import samwells.io.netflix_api.config.JwtConfig;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    @Override
    public String generateJwt(Long userId) {
        Instant currentTime = Instant.now();
        Instant expirationTime = currentTime.plus(jwtConfig.getExpirationInMs(), ChronoUnit.MILLIS);
        String subject = String.valueOf(userId);
        String issuer = jwtConfig.getIssuer();
        String audience = jwtConfig.getAudience();

        return Jwts.builder()
                .issuedAt(Date.from(currentTime))
                .expiration(Date.from(expirationTime))
                .subject(subject)
                .issuer(issuer)
                .audience().add(audience).and()
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    @Override
    public boolean validateJwt(String jwt) {
        return false;
    }
}
