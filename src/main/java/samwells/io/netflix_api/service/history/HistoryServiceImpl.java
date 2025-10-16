package samwells.io.netflix_api.service.history;

import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samwells.io.netflix_api.entity.*;
import samwells.io.netflix_api.exception.DataConflictException;
import samwells.io.netflix_api.exception.ResourceNotFoundException;
import samwells.io.netflix_api.model.history.WatchHistory;
import samwells.io.netflix_api.repository.MediaRepository;
import samwells.io.netflix_api.repository.TvShowEpisodeRepository;
import samwells.io.netflix_api.repository.UserRepository;
import samwells.io.netflix_api.repository.UserWatchHistoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final UserWatchHistoryRepository userWatchHistoryRepository;
    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;
    private final TvShowEpisodeRepository tvShowEpisodeRepository;

    @Override
    @Transactional
    public void addMovieHistory(Long movieId, Long userId) {
        try {
            Media media = mediaRepository.getReferenceById(movieId);
            User user = userRepository.getReferenceById(userId);

            UserWatchHistory watchHistory = new UserWatchHistory();
            watchHistory.setMedia(media);
            watchHistory.setUser(user);

            userWatchHistoryRepository.save(watchHistory);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) throw new DataConflictException(String.format("Movie %s has already been marked as watched.", movieId));
            throw e;
        }
    }

    @Override
    @Transactional
    public void addTvShowEpisodeHistory(Long showEpisodeId, Long userId) {
        try {
            TvShowEpisode tvShow = tvShowEpisodeRepository.getTvShowEpisodeWithMedia(showEpisodeId)
                    .orElseThrow(() -> new ResourceNotFoundException(showEpisodeId));
            Media media = mediaRepository.getReferenceById(tvShow.getMedia().getId());
            User user = userRepository.getReferenceById(userId);

            UserWatchHistory watchHistory = new UserWatchHistory();
            watchHistory.setMedia(media);
            watchHistory.setUser(user);

            userWatchHistoryRepository.save(watchHistory);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) throw new DataConflictException(String.format("Show %s has already been marked as watched.", showEpisodeId));
            throw e;
        }
    }

    @Override
    public List<WatchHistory> getWatchHistory(Long userId, Pageable pageable) {
        return userWatchHistoryRepository.getWatchHistory(userId, pageable).toList();
    }
}
