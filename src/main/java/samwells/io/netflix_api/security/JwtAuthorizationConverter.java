package samwells.io.netflix_api.security;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import samwells.io.netflix_api.service.user.UserDetailsService;

import java.util.List;

@Component
@AllArgsConstructor
public class JwtAuthorizationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final UserDetailsService userDetailsService;

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        try {
            Long userId = Long.valueOf(source.getClaim("sub"));

            if (!userDetailsService.userExists(userId)) throw new UsernameNotFoundException("User not found");

            return new JwtAuthenticationToken(source, List.of());
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
