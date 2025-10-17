package samwells.io.netflix_api.dto.response;

public record CursorPaginatedResponse<T>(
        T data,
        String cursor
) { }
