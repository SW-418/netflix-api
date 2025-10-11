package samwells.io.netflix_api.model.tvshow;

import samwells.io.netflix_api.model.Genre;

public record TvShow(
        Long id,
        String name,
        String description,
        Genre genre,
        Integer seasons
) {
    public TvShow(samwells.io.netflix_api.entity.TvShow tvShow) {
        this (
                tvShow.getId(),
                tvShow.getName(),
                tvShow.getDescription(),
                tvShow.getGenre().getName(),
                tvShow.getSeasonCount()
        );
    }
}
