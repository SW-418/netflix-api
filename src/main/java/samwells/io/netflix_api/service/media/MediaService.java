package samwells.io.netflix_api.service.media;

import samwells.io.netflix_api.entity.Media;
import samwells.io.netflix_api.model.MediaType;

public interface MediaService {
    boolean existsByIdAndMediaType(Long id, MediaType mediaType);
    Media getReferenceById(Long id);
}
