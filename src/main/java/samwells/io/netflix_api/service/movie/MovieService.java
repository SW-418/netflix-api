package samwells.io.netflix_api.service.movie;

import samwells.io.netflix_api.model.Movie;
import samwells.io.netflix_api.model.MovieFilter;

import java.util.List;

public interface MovieService {
    List<Movie> getMovies(MovieFilter filter);
}
