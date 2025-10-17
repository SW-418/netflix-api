package samwells.io.netflix_api.service.history;

import samwells.io.netflix_api.model.history.PaginatedWatchHistory;

public interface HistoryService {
    void addMovieHistory(Long movieId, Long userId);
    void addTvShowEpisodeHistory(Long showEpisodeId, Long userId);
    PaginatedWatchHistory getWatchHistory(Long userId, int size, String cursor);
}
