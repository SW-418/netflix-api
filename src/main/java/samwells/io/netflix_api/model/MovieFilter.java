package samwells.io.netflix_api.model;

import org.springframework.data.domain.Pageable;

public record MovieFilter(
        Genre genre,
        Integer minRating,
        Pageable pageable
) { }
