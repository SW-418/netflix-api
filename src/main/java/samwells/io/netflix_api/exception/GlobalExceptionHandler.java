package samwells.io.netflix_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import samwells.io.netflix_api.dto.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(exception = { UserAlreadyExistsException.class, LoginFailedException.class })
    ResponseEntity<ErrorResponse> handleBadRequest(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleAllExceptions(Exception e) {
        return ResponseEntity.internalServerError().body(new ErrorResponse("Internal Server Error"));
    }
}
