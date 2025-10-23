package samwells.io.netflix_api.dto.request.history;

import jakarta.validation.constraints.NotNull;

public record UserHistoryRequest(
    @NotNull Long mediaId
) { }
