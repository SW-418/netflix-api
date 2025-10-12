package samwells.io.netflix_api.service.tvshow;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samwells.io.netflix_api.exception.ResourceNotFoundException;
import samwells.io.netflix_api.model.tvshow.TvShow;
import samwells.io.netflix_api.model.tvshow.TvShowFilter;
import samwells.io.netflix_api.model.tvshow.TvShowSeason;
import samwells.io.netflix_api.model.tvshow.TvShowWithMetadata;
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

    @Override
    public TvShow getTvShow(Long id) {
        TvShowWithMetadata show = tvShowRepository
                .getTvShow(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new TvShow(show);
    }

    @Override
    @Transactional
    public List<TvShowSeason> getTvShowSeasons(Long id, Pageable pageable) {
        // This is needed because subsequent query will return empty list if show doesn't exist which is misleading
        if (!tvShowRepository.existsById(id)) throw new ResourceNotFoundException(id);

        return tvShowRepository
                .getTvShowSeasons(id, pageable)
                .stream()
                .map(TvShowSeason::new)
                .toList();
    }
}
