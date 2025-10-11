package samwells.io.netflix_api.dto.response;

import samwells.io.netflix_api.model.Genre;
import samwells.io.netflix_api.model.tvshow.TvShow;

public record TvShowResponse(
    Long id,
    String name,
    Genre genre,
    Integer seasons
) {
    public TvShowResponse(TvShow tvShow) {
        this (
                tvShow.id(),
                tvShow.name(),
                tvShow.genre(),
                tvShow.seasons()
        );
    }
}
