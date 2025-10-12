package samwells.io.netflix_api.service.history;

public interface HistoryService {
    void addMovieHistory(Long movieId, Long userId);
    void addTvShowEpisodeHistory(Long showEpisodeId, Long userId);
}
