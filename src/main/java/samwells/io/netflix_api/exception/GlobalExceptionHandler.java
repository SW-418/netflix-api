package samwells.io.netflix_api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import samwells.io.netflix_api.dto.response.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(exception = { UserAlreadyExistsException.class, LoginFailedException.class })
    ResponseEntity<ErrorResponse> handleBadRequest(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(exception = { ResourceNotFoundException.class })
    ResponseEntity<ErrorResponse> handleNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleAllExceptions(Exception e) {
        log.error("Encountered uncaught exception", e);
        return ResponseEntity.internalServerError().body(new ErrorResponse("Internal Server Error"));
    }
}
