package samwells.io.netflix_api.model.tvshow;

import java.time.LocalDate;

public record TvShowSeason(
        Long id,
        String name,
        String description,
        LocalDate releaseDate,
        Long episodes
) {
    public TvShowSeason(TvShowSeasonWithMetadata tvShowSeason) {
        this (
                tvShowSeason.id(),
                tvShowSeason.name(),
                tvShowSeason.description(),
                tvShowSeason.releaseDate(),
                tvShowSeason.episodeCount()
        );
    }
}
