package samwells.io.netflix_api.service.tvshow;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import samwells.io.netflix_api.model.tvshow.TvShow;
import samwells.io.netflix_api.model.tvshow.TvShowFilter;
import samwells.io.netflix_api.repository.TvShowRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TvShowServiceImpl implements TvShowService {
    private final TvShowRepository tvShowRepository;

    @Override
    public List<TvShow> getTvShows(TvShowFilter filter) {
        return tvShowRepository
                .getTvShows(filter.genre(), filter.pageable())
                .stream()
                .map(TvShow::new)
                .toList();
    }
}
