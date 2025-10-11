package samwells.io.netflix_api.model.tvshow;

import org.springframework.data.domain.Pageable;
import samwells.io.netflix_api.model.Genre;

public record TvShowFilter(
        Genre genre,
        Integer minRating,
        Pageable pageable
) { }
