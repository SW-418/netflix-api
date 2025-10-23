package samwells.io.netflix_api.exception;

public class UnsupportedMediaTypeException extends RuntimeException {
    public UnsupportedMediaTypeException(Long mediaId) {
        super(String.format("Requested operation is not supported for provided resource %s", mediaId));
    }
}
