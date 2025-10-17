package samwells.io.netflix_api.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import samwells.io.netflix_api.service.user.UserDetailsService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class JwtAuthorizationConverterTest {
    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthorizationConverter converter;

    @Test
    void convert_withJwtWithoutSubClaim_throwsUsernameNotFoundException() {
        Jwt jwt = new Jwt(
                "test-token-value",
                Instant.now(),
                Instant.now().plus(5, ChronoUnit.MINUTES),
                Map.of("alg", "HS256"),
                Map.of("iss", "netflix-api-auth")
        );

        Assertions.assertThrows(UsernameNotFoundException.class, () -> converter.convert(jwt));
    }

    @Test
    void convert_withJwtWithoutExistingUser_throwsUsernameNotFoundException() {
        String userId = "123";
        Long parsedUserId = Long.valueOf(userId);

        Mockito.when(userDetailsService.userExists(parsedUserId)).thenReturn(false);

        Jwt jwt = new Jwt(
                "test-token-value",
                Instant.now(),
                Instant.now().plus(5, ChronoUnit.MINUTES),
                Map.of("alg", "HS256"),
                Map.of(
                        "iss", "netflix-api-auth",
                        "sub", userId
                )
        );

        Assertions.assertThrows(UsernameNotFoundException.class, () -> converter.convert(jwt));
    }

    @Test
    void convert_withJwtWithExistingUser_doesNotThrowException() {
        String userId = "123";
        Long parsedUserId = Long.valueOf(userId);

        Mockito.when(userDetailsService.userExists(parsedUserId)).thenReturn(true);

        Jwt jwt = new Jwt(
                "test-token-value",
                Instant.now(),
                Instant.now().plus(5, ChronoUnit.MINUTES),
                Map.of("alg", "HS256"),
                Map.of(
                        "iss", "netflix-api-auth",
                        "sub", userId
                )
        );

        Assertions.assertDoesNotThrow(() -> converter.convert(jwt));
    }
}