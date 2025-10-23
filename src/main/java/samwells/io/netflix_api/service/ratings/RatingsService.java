package samwells.io.netflix_api.service.ratings;

import samwells.io.netflix_api.model.MediaType;

public interface RatingsService {
    void addRating(Long userId, Long contentId, MediaType mediaType, int score);
}
