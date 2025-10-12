package samwells.io.netflix_api.dto.response;

import samwells.io.netflix_api.model.tvshow.TvShowSeason;

import java.time.LocalDate;

public record TvShowSeasonResponse(
    Long id,
    String name,
    String description,
    LocalDate releaseDate,
    Long episodes
) {
    public TvShowSeasonResponse(TvShowSeason season) {
        this (
                season.id(),
                season.name(),
                season.description(),
                season.releaseDate(),
                season.episodes()
        );
    }
}
