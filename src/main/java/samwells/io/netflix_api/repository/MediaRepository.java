package samwells.io.netflix_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import samwells.io.netflix_api.entity.Media;
import samwells.io.netflix_api.model.Genre;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    @Query("""
            SELECT m
            FROM Media m
            WHERE m.mediaType.name = 'MOVIE'
            AND (:genre IS NULL OR m.genre.name = :genre)
            """)
    Page<Media> getMovies(
            @Param("genre") Genre genre,
            Pageable pageable
    );
}
