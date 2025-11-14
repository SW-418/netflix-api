package samwells.io.netflix_api.service.movie;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import samwells.io.netflix_api.entity.Media;
import samwells.io.netflix_api.model.Genre;
import samwells.io.netflix_api.model.movie.Movie;
import samwells.io.netflix_api.model.movie.MovieFilter;
import samwells.io.netflix_api.repository.MovieRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getMovies(MovieFilter filter) {
        // TODO: Add rating filtering
        Page<Media> movies = movieRepository.getMovies(filter.genre(), filter.pageable());

        return movies.get().map(Movie::new).toList();
    }

    @Override
    public List<Movie> getTopRatedMovies(Genre genre, Pageable pageable) {
        Page<Media> movies = movieRepository.getTopRatedMovies(genre, pageable);

        return movies.get().map(Movie::new).toList();
    }
}
