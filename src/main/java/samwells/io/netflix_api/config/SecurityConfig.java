package samwells.io.netflix_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.sessionManagement(AbstractHttpConfigurer::disable);

        httpSecurity.authorizeHttpRequests(http -> {
            http.requestMatchers("/api/v1/auth/register", "/api/v1/auth/register").permitAll();
            http.anyRequest().authenticated();
        });

        return httpSecurity.build();
    }
}
