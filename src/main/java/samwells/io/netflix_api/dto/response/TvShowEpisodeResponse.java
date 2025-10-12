package samwells.io.netflix_api.dto.response;

import samwells.io.netflix_api.model.tvshow.TvShowEpisode;

import java.time.LocalDate;

public record TvShowEpisodeResponse(
    Long id,
    String name,
    String description,
    Integer episodeNumber,
    LocalDate releaseDate
) {
    public TvShowEpisodeResponse(TvShowEpisode episode) {
        this (
                episode.id(),
                episode.name(),
                episode.description(),
                episode.episodeNumber(),
                episode.releaseDate()
        );
    }
}
