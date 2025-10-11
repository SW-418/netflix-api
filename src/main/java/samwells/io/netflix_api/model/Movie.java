package samwells.io.netflix_api.model;

import samwells.io.netflix_api.entity.Media;

public record Movie(
        Long id,
        String name,
        Genre genre
) {
    public Movie(Media media) {
        this (
                media.getId(),
                media.getName(),
                media.getGenre().getName()
        );
    }
}
