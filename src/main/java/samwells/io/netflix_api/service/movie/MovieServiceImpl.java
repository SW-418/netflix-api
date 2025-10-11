package samwells.io.netflix_api.service.movie;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import samwells.io.netflix_api.entity.Media;
import samwells.io.netflix_api.model.Movie;
import samwells.io.netflix_api.model.MovieFilter;
import samwells.io.netflix_api.repository.MediaRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MediaRepository mediaRepository;

    @Override
    public List<Movie> getMovies(MovieFilter filter) {
        // TODO: Add rating filtering
        Page<Media> movies = mediaRepository.getMovies(filter.genre(), filter.pageable());

        return movies.get().map(Movie::new).toList();
    }
}
