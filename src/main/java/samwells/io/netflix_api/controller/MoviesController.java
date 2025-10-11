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
import samwells.io.netflix_api.dto.response.MovieResponse;
import samwells.io.netflix_api.model.Genre;
import samwells.io.netflix_api.model.Movie;
import samwells.io.netflix_api.model.MovieFilter;
import samwells.io.netflix_api.service.movie.MovieService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/movies")
public class MoviesController {
    private final MovieService movieService;

    @GetMapping
    ResponseEntity<List<MovieResponse>> getMovies(
            @RequestParam(required = false) Genre genre,
            @RequestParam(required = false) @Min(1) @Max(5) Integer minRating,
            @PageableDefault(page = 0, size = 15) Pageable pageable
    ) {
        List<Movie> movies = movieService.getMovies(new MovieFilter(genre, minRating, pageable));
        return ResponseEntity.ok(movies.stream().map(MovieResponse::new).toList());
    }
}
