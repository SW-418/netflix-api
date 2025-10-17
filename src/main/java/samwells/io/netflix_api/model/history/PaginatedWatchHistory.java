package samwells.io.netflix_api.model.history;

import java.util.List;

public record PaginatedWatchHistory(
    List<WatchHistory> watchHistory,
    String cursor
) { }
