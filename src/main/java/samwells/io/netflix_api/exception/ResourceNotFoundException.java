package samwells.io.netflix_api.exception;

import samwells.io.netflix_api.model.MediaType;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super(String.format("Requested resource with id %s could not be found.", id));
    }

    public ResourceNotFoundException(Long id, MediaType mediaType) {
        super(String.format("Requested resource with id %s could not be found for media type %s.", id, mediaType));
    }
}
