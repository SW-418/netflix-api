package samwells.io.netflix_api.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import samwells.io.netflix_api.dto.response.TvShowResponse;
import samwells.io.netflix_api.model.Genre;
import samwells.io.netflix_api.model.tvshow.TvShowFilter;
import samwells.io.netflix_api.service.tvshow.TvShowService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tv-shows")
public class TvShowsController {
    private final TvShowService tvShowService;

    @GetMapping
    ResponseEntity<List<TvShowResponse>> getTvShows(
            @RequestParam(required = false) Genre genre,
            @RequestParam(required = false) @Min(1) @Max(5) Integer minRating,
            @PageableDefault(page = 0, size = 15) Pageable pageable
    ) {
        List<TvShowResponse> shows = tvShowService
                .getTvShows(new TvShowFilter(genre, minRating, pageable))
                .stream()
                .map(TvShowResponse::new)
                .toList();

        return ResponseEntity.ok(shows);
    }
}
