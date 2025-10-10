package samwells.io.netflix_api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samwells.io.netflix_api.dto.request.RegistrationRequest;
import samwells.io.netflix_api.dto.response.LoginResponse;
import samwells.io.netflix_api.service.UserDetailsService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserDetailsService userDetailsService;

    @PostMapping("/register")
    ResponseEntity<Void> register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        userDetailsService.createUser(
                new User(registrationRequest.username(), registrationRequest.password(), List.of())
        );
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login() {

        return ResponseEntity.ok(new LoginResponse(""));
    }
}
