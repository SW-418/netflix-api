package samwells.io.netflix_api.service.tvshow;

import samwells.io.netflix_api.model.tvshow.TvShow;
import samwells.io.netflix_api.model.tvshow.TvShowFilter;

import java.util.List;

public interface TvShowService {
    List<TvShow> getTvShows(TvShowFilter filter);
    TvShow getTvShow(Long id);
}
