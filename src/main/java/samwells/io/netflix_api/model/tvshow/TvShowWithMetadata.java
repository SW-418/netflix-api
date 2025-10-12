package samwells.io.netflix_api.model.tvshow;

import samwells.io.netflix_api.model.Genre;

public record TvShowWithMetadata(
        Long id,
        String name,
        String description,
        Genre genre,
        Long seasonCount,
        Long episodeCount
) {}
