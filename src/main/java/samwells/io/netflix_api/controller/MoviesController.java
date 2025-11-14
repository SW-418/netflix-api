package samwells.io.netflix_api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import samwells.io.netflix_api.dto.request.RatingRequest;
import samwells.io.netflix_api.dto.response.MovieResponse;
import samwells.io.netflix_api.model.Genre;
import samwells.io.netflix_api.model.MediaType;
import samwells.io.netflix_api.model.movie.Movie;
import samwells.io.netflix_api.model.movie.MovieFilter;
import samwells.io.netflix_api.service.movie.MovieService;
import samwells.io.netflix_api.service.ratings.RatingsService;
import samwells.io.netflix_api.util.UserUtil;
import samwells.io.netflix_api.validation.ValidEnum;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/movies")
public class MoviesController {
    private final MovieService movieService;
    private final RatingsService ratingsService;

    @GetMapping
    ResponseEntity<List<MovieResponse>> getMovies(
            @RequestParam(required = false) @Validated @ValidEnum(enumClass = Genre.class, required = false) String genre,
            @RequestParam(required = false) @Min(1) @Max(5) Integer minRating,
            @PageableDefault(page = 0, size = 15) Pageable pageable
    ) {
        Genre parsedGenre = genre == null ? null : Genre.valueOf(genre);
        List<Movie> movies = movieService.getMovies(new MovieFilter(parsedGenre, minRating, pageable));
        return ResponseEntity.ok(movies.stream().map(MovieResponse::new).toList());
    }

    @GetMapping(path = "/top-rated")
    ResponseEntity<List<MovieResponse>> getTopRatedMovies(
            @RequestParam(required = false) @Validated @ValidEnum(enumClass = Genre.class, required = false) String genre,
            @PageableDefault(page = 0, size = 15) Pageable pageable
    ) {
        Genre parsedGenre = genre == null ? null : Genre.valueOf(genre);
        List<Movie> movies = movieService.getTopRatedMovies(parsedGenre, pageable);

        return ResponseEntity.ok(movies.stream().map(MovieResponse::new).toList());
    }

    @PutMapping("/{id}/ratings")
    ResponseEntity<Void> rateMovie(@PathVariable Long id, @RequestBody @Valid RatingRequest request) {
        ratingsService.addRating(UserUtil.getUserId(), id, MediaType.MOVIE, request.rating());

        return ResponseEntity.ok().build();
    }
}
