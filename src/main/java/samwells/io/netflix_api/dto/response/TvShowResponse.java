package samwells.io.netflix_api.dto.response;

import samwells.io.netflix_api.model.Genre;
import samwells.io.netflix_api.model.tvshow.TvShow;

public record TvShowResponse(
    Long id,
    String name,
    String description,
    Genre genre,
    Long seasons,
    Long episodes
) {
    public TvShowResponse(TvShow tvShow) {
        this (
                tvShow.id(),
                tvShow.name(),
                tvShow.description(),
                tvShow.genre(),
                tvShow.seasons(),
                tvShow.episodes()
        );
    }
}
