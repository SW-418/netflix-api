package samwells.io.netflix_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCredentialsRequest(
        @NotNull @Size(min = 1, max = 100) @Email String username,
        @NotNull @Size(min = 1, max = 200) String password
) { }
