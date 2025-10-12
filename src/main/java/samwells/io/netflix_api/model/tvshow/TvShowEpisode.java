package samwells.io.netflix_api.model.tvshow;

import java.time.LocalDate;

public record TvShowEpisode(
        Long id,
        String name,
        String description,
        Integer episodeNumber,
        LocalDate releaseDate
) { }
