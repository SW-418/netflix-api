package samwells.io.netflix_api.dto.response.history;

import samwells.io.netflix_api.model.MediaType;
import samwells.io.netflix_api.model.history.WatchHistory;

import java.time.Instant;

public record WatchHistoryResponse(
        String name,
        MediaType mediaType,
        Instant watchedAt
) {
    public WatchHistoryResponse(WatchHistory watchHistory) {
        this (
                watchHistory.name(),
                watchHistory.mediaType(),
                watchHistory.watchedAt()
        );
    }
}
