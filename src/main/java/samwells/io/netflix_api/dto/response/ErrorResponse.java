package samwells.io.netflix_api.dto.response;

import java.util.List;

public record ErrorResponse(
        String message,
        List<String> errorDetails
) {
    public ErrorResponse (String message) {
        this (
                message,
                List.of()
        );
    }
}
