package samwells.io.netflix_api.model.tvshow;

import samwells.io.netflix_api.model.Genre;

public record TvShow(
        Long id,
        String name,
        String description,
        Genre genre,
        Long seasons,
        Long episodes
) {
    public TvShow(TvShowWithMetadata tvShow) {
        this (
                tvShow.id(),
                tvShow.name(),
                tvShow.description(),
                tvShow.genre(),
                tvShow.seasonCount(),
                tvShow.episodeCount()
        );
    }
}
