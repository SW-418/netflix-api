package samwells.io.netflix_api.service.history;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samwells.io.netflix_api.entity.*;
import samwells.io.netflix_api.exception.ResourceNotFoundException;
import samwells.io.netflix_api.exception.UnsupportedMediaTypeException;
import samwells.io.netflix_api.exception.UserNotFoundException;
import samwells.io.netflix_api.model.MediaType;
import samwells.io.netflix_api.model.PaginationCursor;
import samwells.io.netflix_api.model.history.PaginatedWatchHistory;
import samwells.io.netflix_api.model.history.WatchHistory;
import samwells.io.netflix_api.repository.MediaRepository;
import samwells.io.netflix_api.repository.UserRepository;
import samwells.io.netflix_api.repository.UserWatchHistoryRepository;
import samwells.io.netflix_api.util.PaginationUtil;

import java.time.Instant;
import java.util.EnumSet;
import java.util.List;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final UserWatchHistoryRepository userWatchHistoryRepository;
    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;
    private final EnumSet<MediaType> ALLOWABLE_MEDIA_TYPES = EnumSet.of(MediaType.MOVIE, MediaType.TV_SHOW_EPISODE);

    @Override
    @Transactional
    public void addHistory(Long mediaId, Long userId) {
        try {
            Media media = mediaRepository.getReferenceById(mediaId);
            if (!ALLOWABLE_MEDIA_TYPES.contains(media.getMediaType().getName())) throw new UnsupportedMediaTypeException(mediaId);

            User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

            UserWatchHistory watchHistory = new UserWatchHistory(user, media);

            userWatchHistoryRepository.save(watchHistory);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(mediaId);
        }
    }

    @Override
    public PaginatedWatchHistory getWatchHistory(Long userId, int size, String cursor) {
        if (cursor == null || cursor.isEmpty()) return getWatchHistory(userId, null, null, size);

        PaginationCursor paginationCursor = PaginationUtil.decode(cursor);
        return getWatchHistory(userId, paginationCursor.timestamp(), paginationCursor.id(), size);
    }

    private PaginatedWatchHistory getWatchHistory(Long userId, Instant afterTimestamp, Long afterId, int size) {
         List<WatchHistory> watchHistory = userWatchHistoryRepository.getWatchHistory(
                userId,
                afterTimestamp,
                afterId,
                Pageable.ofSize(size)
        ).toList();

         if (watchHistory.isEmpty()) return new PaginatedWatchHistory(watchHistory, null);

         return new PaginatedWatchHistory(
                 watchHistory, PaginationUtil.encode(watchHistory.getLast().watchedAt(), watchHistory.getLast().id())
         );
    }
}
