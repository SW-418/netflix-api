package samwells.io.netflix_api.model;

import java.time.Instant;

public record PaginationCursor(
    Instant timestamp,
    Long id
) { }
