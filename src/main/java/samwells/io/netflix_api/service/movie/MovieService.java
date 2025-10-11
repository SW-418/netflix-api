package samwells.io.netflix_api.service.movie;

import samwells.io.netflix_api.model.movie.Movie;
import samwells.io.netflix_api.model.movie.MovieFilter;

import java.util.List;

public interface MovieService {
    List<Movie> getMovies(MovieFilter filter);
}
