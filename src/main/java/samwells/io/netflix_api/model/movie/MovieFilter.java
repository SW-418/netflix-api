package samwells.io.netflix_api.model.movie;

import org.springframework.data.domain.Pageable;
import samwells.io.netflix_api.model.Genre;

public record MovieFilter(
        Genre genre,
        Integer minRating,
        Pageable pageable
) { }
