package samwells.io.netflix_api.service.media;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samwells.io.netflix_api.entity.Media;
import samwells.io.netflix_api.model.MediaType;
import samwells.io.netflix_api.repository.MediaRepository;

@Service
@AllArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean existsByIdAndMediaType(Long id, MediaType mediaType) {
        return mediaRepository.existsByIdAndMediaType(id, mediaType);
    }

    @Override
    @Transactional(readOnly = true)
    public Media getReferenceById(Long id) {
        return mediaRepository.getReferenceById(id);
    }
}
