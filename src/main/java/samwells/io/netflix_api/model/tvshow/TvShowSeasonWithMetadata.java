package samwells.io.netflix_api.model.tvshow;

import java.time.LocalDate;

public record TvShowSeasonWithMetadata(
        Long id,
        String name,
        String description,
        LocalDate releaseDate,
        Long episodeCount
) {}
