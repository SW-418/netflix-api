package samwells.io.netflix_api.dto.request.history;

public record UserHistoryRequest(
    Long movieId,
    Long tvShowEpisodeId
) { }
