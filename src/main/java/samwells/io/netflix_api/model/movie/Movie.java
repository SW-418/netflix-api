package samwells.io.netflix_api.model.movie;

import samwells.io.netflix_api.entity.Media;
import samwells.io.netflix_api.model.Genre;

import java.time.LocalDate;

public record Movie(
        Long id,
        String name,
        String description,
        Genre genre,
        LocalDate releaseDate
) {
    public Movie(Media media) {
        this (
                media.getId(),
                media.getName(),
                media.getDescription(),
                media.getGenre().getName(),
                media.getReleaseDate()
        );
    }
}
