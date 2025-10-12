package samwells.io.netflix_api.service.tvshow;

import org.springframework.data.domain.Pageable;
import samwells.io.netflix_api.model.tvshow.TvShow;
import samwells.io.netflix_api.model.tvshow.TvShowEpisode;
import samwells.io.netflix_api.model.tvshow.TvShowFilter;
import samwells.io.netflix_api.model.tvshow.TvShowSeason;

import java.util.List;

public interface TvShowService {
    List<TvShow> getTvShows(TvShowFilter filter);
    TvShow getTvShow(Long id);
    List<TvShowSeason> getTvShowSeasons(Long id, Pageable pageable);
    List<TvShowEpisode> getTvShowEpisodes(Long tvShowId, Long seasonId, Pageable pageable);
}
