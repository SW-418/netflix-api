package samwells.io.netflix_api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import samwells.io.netflix_api.dto.response.ErrorResponse;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(exception = { DataConflictException.class })
    ResponseEntity<ErrorResponse> handleConflict(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(exception = { UserAlreadyExistsException.class, LoginFailedException.class, BadRequestException.class })
    ResponseEntity<ErrorResponse> handleBadRequest(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(exception = { HandlerMethodValidationException.class })
    ResponseEntity<ErrorResponse> handleBadRequestValidation(HandlerMethodValidationException e) {
        List<String> errors = e.getAllErrors().stream().map(MessageSourceResolvable::getDefaultMessage).toList();
        return ResponseEntity.badRequest().body(new ErrorResponse("Validation error", errors));
    }

    @ExceptionHandler(exception = { HttpMessageNotReadableException.class })
    ResponseEntity<ErrorResponse> handleMalformedRequest(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorResponse("Malformed request body"));
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
