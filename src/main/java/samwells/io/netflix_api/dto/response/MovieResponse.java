package samwells.io.netflix_api.dto.response;

import samwells.io.netflix_api.model.Genre;
import samwells.io.netflix_api.model.movie.Movie;

import java.time.LocalDate;

public record MovieResponse(
    Long id,
    String name,
    String description,
    Genre genre,
    LocalDate releaseDate
) {
    public MovieResponse(Movie movie) {
        this (
                movie.id(),
                movie.description(),
                movie.name(),
                movie.genre(),
                movie.releaseDate()
        );
    }
}
