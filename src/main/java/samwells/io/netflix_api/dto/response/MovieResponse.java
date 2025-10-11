package samwells.io.netflix_api.dto.response;

import samwells.io.netflix_api.model.Genre;
import samwells.io.netflix_api.model.Movie;

public record MovieResponse(
    Long id,
    String name,
    Genre genre
) {
    public MovieResponse(Movie movie) {
        this (
                movie.id(),
                movie.name(),
                movie.genre()
        );
    }
}
