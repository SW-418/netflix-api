package samwells.io.netflix_api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samwells.io.netflix_api.dto.request.RatingRequest;
import samwells.io.netflix_api.dto.response.TvShowEpisodeResponse;
import samwells.io.netflix_api.dto.response.TvShowResponse;
import samwells.io.netflix_api.dto.response.TvShowSeasonResponse;
import samwells.io.netflix_api.model.Genre;
import samwells.io.netflix_api.model.MediaType;
import samwells.io.netflix_api.model.tvshow.TvShow;
import samwells.io.netflix_api.model.tvshow.TvShowFilter;
import samwells.io.netflix_api.service.ratings.RatingsService;
import samwells.io.netflix_api.service.tvshow.TvShowService;
import samwells.io.netflix_api.util.UserUtil;
import samwells.io.netflix_api.validation.ValidEnum;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tv-shows")
public class TvShowsController {
    private final TvShowService tvShowService;
    private final RatingsService ratingsService;

    @GetMapping
    ResponseEntity<List<TvShowResponse>> getTvShows(
            @RequestParam(required = false) @ValidEnum(enumClass = Genre.class, required = false) String genre,
            @RequestParam(required = false) @Min(1) @Max(5) Integer minRating,
            @PageableDefault(page = 0, size = 15) Pageable pageable
    ) {
        Genre parsedGenre = genre == null ? null : Genre.valueOf(genre);
        List<TvShowResponse> shows = tvShowService
                .getTvShows(new TvShowFilter(parsedGenre, minRating, pageable))
                .stream()
                .map(TvShowResponse::new)
                .toList();

        return ResponseEntity.ok(shows);
    }

    @GetMapping("/{id}")
    ResponseEntity<TvShowResponse> getTvShow(
            @PathVariable("id") Long id
    ) {
        TvShow show = tvShowService.getTvShow(id);
        return ResponseEntity.ok(new TvShowResponse(show));
    }

    @GetMapping("/{tvShowId}/seasons")
    ResponseEntity<List<TvShowSeasonResponse>> getTvShowSeasons(
            @PathVariable("tvShowId") Long tvShowId,
            @PageableDefault Pageable pageable
    ) {
        List<TvShowSeasonResponse> seasons = tvShowService
                .getTvShowSeasons(tvShowId, pageable)
                .stream()
                .map(TvShowSeasonResponse::new)
                .toList();

        return ResponseEntity.ok(seasons);
    }

    @GetMapping("/{tvShowId}/seasons/{seasonId}/episodes")
    ResponseEntity<List<TvShowEpisodeResponse>> getTvShowSeasonEpisodes(
            @PathVariable("tvShowId") Long tvShowId,
            @PathVariable("seasonId") Long seasonId,
            @PageableDefault Pageable pageable
    ) {
        List<TvShowEpisodeResponse> seasons = tvShowService
                .getTvShowEpisodes(tvShowId, seasonId, pageable)
                .stream()
                .map(TvShowEpisodeResponse::new)
                .toList();

        return ResponseEntity.ok(seasons);
    }

    @PutMapping("/episodes/{episodeId}/ratings")
    ResponseEntity<Void> getTvShowSeasonEpisodes(
            @PathVariable("episodeId") Long episodeId,
            @RequestBody @Valid RatingRequest request
    ) {
        ratingsService.addRating(UserUtil.getUserId(), episodeId, MediaType.TV_SHOW_EPISODE, request.rating());

        return ResponseEntity.ok().build();
    }
}
