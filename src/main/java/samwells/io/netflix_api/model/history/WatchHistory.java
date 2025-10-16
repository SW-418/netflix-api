package samwells.io.netflix_api.model.history;

import samwells.io.netflix_api.model.MediaType;

import java.time.Instant;

public record WatchHistory(
        Long id,
        // Movie Name OR TV show name
        String name,
        MediaType mediaType,
        Instant watchedAt
) {
    public WatchHistory(Long id, String name, String mediaType, Instant watchedAt) {
        this (
                id,
                name,
                MediaType.valueOf(mediaType),
                watchedAt
        );
    }
}
