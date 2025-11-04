package samwells.io.netflix_api.service.ratings;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samwells.io.netflix_api.entity.Media;
import samwells.io.netflix_api.entity.MediaRating;
import samwells.io.netflix_api.entity.User;
import samwells.io.netflix_api.exception.ResourceNotFoundException;
import samwells.io.netflix_api.model.MediaType;
import samwells.io.netflix_api.repository.MediaRatingRepository;
import samwells.io.netflix_api.service.media.MediaService;
import samwells.io.netflix_api.service.user.UserDetailsService;

@Service
@AllArgsConstructor
public class RatingsServiceImpl implements RatingsService {
    private final MediaService mediaService;
    private final UserDetailsService userDetailsService;
    private final MediaRatingRepository mediaRatingRepository;

    @Override
    @Transactional
    public void addRating(Long userId, Long contentId, MediaType mediaType, int score) {
        // Check existence first to validate media is of correct type
        if (!mediaService.existsByIdAndMediaType(contentId, mediaType)) throw new ResourceNotFoundException(contentId, mediaType);

        // Use references here to prevent having to load from the DB
        // FK constraints will prevent insertions/updates if references don't exist
        User userReference = userDetailsService.getReferenceById(userId);
        Media mediaReference = mediaService.getReferenceById(contentId);

        MediaRating mediaRating = mediaRatingRepository
                .findByMediaIdAndUserId(contentId, userId)
                .orElse(new MediaRating(userReference, mediaReference));

        mediaRating.setScore(score);
        mediaRatingRepository.save(mediaRating);
    }
}
