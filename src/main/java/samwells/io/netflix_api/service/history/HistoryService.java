package samwells.io.netflix_api.service.history;

import org.springframework.data.domain.Pageable;
import samwells.io.netflix_api.model.history.WatchHistory;

import java.util.List;

public interface HistoryService {
    void addMovieHistory(Long movieId, Long userId);
    void addTvShowEpisodeHistory(Long showEpisodeId, Long userId);
    List<WatchHistory> getWatchHistory(Long userId, Pageable pageable);
}
