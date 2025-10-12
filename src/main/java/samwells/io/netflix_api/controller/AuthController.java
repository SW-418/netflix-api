package samwells.io.netflix_api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samwells.io.netflix_api.dto.request.UserCredentialsRequest;
import samwells.io.netflix_api.dto.response.LoginResponse;
import samwells.io.netflix_api.service.user.UserDetailsServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/register")
    ResponseEntity<Void> register(@Valid @RequestBody UserCredentialsRequest credentialsRequest) {
        userDetailsService.createUser(
                new User(credentialsRequest.username(), credentialsRequest.password(), List.of())
        );
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@Valid @RequestBody UserCredentialsRequest credentialsRequest) {
        String token = userDetailsService.login(credentialsRequest.username(), credentialsRequest.password());

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
