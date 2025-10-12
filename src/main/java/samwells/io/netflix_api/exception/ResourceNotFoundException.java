package samwells.io.netflix_api.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super(String.format("Requested resource with id %s could not be found.", id));
    }
}
