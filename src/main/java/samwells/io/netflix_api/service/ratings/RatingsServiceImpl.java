package samwells.io.netflix_api.service.ratings;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samwells.io.netflix_api.entity.MediaRating;
import samwells.io.netflix_api.entity.User;
import samwells.io.netflix_api.model.MediaType;
import samwells.io.netflix_api.repository.MediaRatingRepository;
import samwells.io.netflix_api.service.user.UserDetailsService;

@Service
@AllArgsConstructor
public class RatingsServiceImpl implements RatingsService {
    private final UserDetailsService userDetailsService;
    private final MediaRatingRepository mediaRatingRepository;

    @Override
    @Transactional
    public void addRating(Long userId, Long contentId, MediaType mediaType, int score) {
        User user = userDetailsService.loadUserById(userId);

        MediaRating rating = new MediaRating();
        rating.setScore(score);
    }
}
